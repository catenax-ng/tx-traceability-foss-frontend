/********************************************************************************
 * Copyright (c) 2022, 2023 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 * Copyright (c) 2022, 2023 ZF Friedrichshafen AG
 * Copyright (c) 2022, 2023 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ********************************************************************************/

package org.eclipse.tractusx.traceability.investigations.adapters.jpa;

import org.eclipse.tractusx.traceability.assets.infrastructure.adapters.jpa.asset.AssetEntity;
import org.eclipse.tractusx.traceability.assets.infrastructure.adapters.jpa.asset.JpaAssetsRepository;
import org.eclipse.tractusx.traceability.common.model.BPN;
import org.eclipse.tractusx.traceability.common.model.PageResult;
import org.eclipse.tractusx.traceability.infrastructure.jpa.investigation.InvestigationEntity;
import org.eclipse.tractusx.traceability.infrastructure.jpa.investigation.JpaInvestigationRepository;
import org.eclipse.tractusx.traceability.infrastructure.jpa.notification.JpaNotificationRepository;
import org.eclipse.tractusx.traceability.infrastructure.jpa.notification.NotificationEntity;
import org.eclipse.tractusx.traceability.investigations.domain.model.*;
import org.eclipse.tractusx.traceability.investigations.domain.ports.InvestigationsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PersistentInvestigationsRepository implements InvestigationsRepository {

    private final JpaInvestigationRepository investigationRepository;

    private final JpaAssetsRepository assetsRepository;

    private final JpaNotificationRepository notificationRepository;

    private final Clock clock;

    public PersistentInvestigationsRepository(JpaInvestigationRepository investigationRepository,
                                              JpaAssetsRepository assetsRepository,
                                              JpaNotificationRepository notificationRepository,
                                              Clock clock) {
        this.investigationRepository = investigationRepository;
        this.assetsRepository = assetsRepository;
        this.notificationRepository = notificationRepository;
        this.clock = clock;
    }

    @Override
    public void update(Notification notification) {
        NotificationEntity entity = notificationRepository.findById(notification.getId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Notification with id %s not found!", notification.getId())));

        update(entity, notification);

        notificationRepository.save(entity);
    }

    @Override
    public InvestigationId update(Investigation investigation) {
        InvestigationEntity investigationEntity = investigationRepository.findById(investigation.getId().value())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Investigation with id %s not found!", investigation.getId().value())));

        update(investigationEntity, investigation);
        investigationRepository.save(investigationEntity);

        return investigation.getId();
    }

    @Override
    public InvestigationId save(Investigation investigation) {
        List<String> assetIds = investigation.getAssetIds();
        List<AssetEntity> assetEntities = assetsRepository.findByIdIn(assetIds);

        if (!assetEntities.isEmpty()) {
            InvestigationEntity investigationEntity = new InvestigationEntity(
                    assetEntities,
                    investigation.getBpn(),
                    investigation.getDescription(),
                    investigation.getInvestigationStatus(),
                    investigation.getInvestigationSide(),
                    investigation.getCreationTime()
            );

            investigationRepository.save(investigationEntity);

            List<NotificationEntity> notifications = investigation.getNotifications().stream()
                    .map(notification -> toNotificationEntity(investigationEntity, notification, assetEntities))
                    .toList();

            notificationRepository.saveAll(notifications);

            return new InvestigationId(investigationEntity.getId());
        } else {
            throw new IllegalArgumentException("No assets found for %s asset ids".formatted(String.join(", ", assetIds)));
        }
    }

    @Override
    public PageResult<Investigation> getInvestigations(Set<InvestigationStatus> investigationStatuses, Pageable pageable) {
        Page<InvestigationEntity> entities = investigationRepository.findAllByStatusInOrderByCreatedDesc(investigationStatuses, pageable);

        return new PageResult<>(entities, this::toInvestigation);
    }

    @Override
    public PageResult<Investigation> getInvestigations(InvestigationSide investigationSide, Pageable pageable) {
        Page<InvestigationEntity> entities = investigationRepository.findAllBySideEqualsOrderByCreatedDesc(investigationSide, pageable);

        return new PageResult<>(entities, this::toInvestigation);
    }

    @Override
    public Optional<Investigation> findById(InvestigationId investigationId) {
        return investigationRepository.findById(investigationId.value())
                .map(this::toInvestigation);
    }

    @Override
    public long countPendingInvestigations() {
        return investigationRepository.countAllByStatusEquals(InvestigationStatus.RECEIVED);
    }

    @Override
    public Optional<Investigation> findByNotificationId(String notificationId) {
        return investigationRepository.findByNotificationsNotificationId(notificationId)
                .map(this::toInvestigation);
    }

    @Override
    public Optional<Investigation> findByNotificationReferenceId(String notificationReferenceId) {
        return investigationRepository.findByNotificationsNotificationReferenceId(notificationReferenceId)
                .map(this::toInvestigation);
    }

    @Override
    public Optional<Investigation> findByEdcNotificationId(String edcNotificationId) {
        return investigationRepository.findByNotificationsEdcNotificationId(edcNotificationId)
                .map(this::toInvestigation);
    }

    @Override
    public long countInvestigations(Set<InvestigationStatus> statuses) {
        return investigationRepository.countAllByStatusIn(statuses);
    }

    @Override
    public long countInvestigations(InvestigationSide investigationSide) {
        return investigationRepository.countAllBySideEquals(investigationSide);
    }

    private void update(InvestigationEntity investigationEntity, Investigation investigation) {
        investigationEntity.setStatus(investigation.getInvestigationStatus());
        investigationEntity.setUpdated(clock.instant());
        investigationEntity.setCloseReason(investigation.getCloseReason());
        investigationEntity.setAcceptReason(investigation.getAcceptReason());
        investigationEntity.setDeclineReason(investigation.getDeclineReason());

        // Persist existing notifications
      /*  investigationEntity.getNotifications()
                .forEach(notification -> investigation.getNotification(notification.getId())
                        .ifPresent(data -> update(notification, data)));*/

        // Persist new notifications
        persistNewNotifications(investigationEntity, investigation);

    }

    private void persistNewNotifications(InvestigationEntity investigationEntity, Investigation investigation) {
        List<Notification> notifications = investigation.getNotifications();
        List<NotificationEntity> notificationEntities = investigationEntity.getNotifications();

        List<Notification> notPersistedNotifications =
                notifications
                        .stream()
                        .filter(notification -> notificationEntities
                                .stream()
                                .anyMatch(notificationEntity -> !notificationEntity.getId().equals(notification.getId())))
                        .toList();

        List<String> assetIds = investigation.getAssetIds();
        List<AssetEntity> assetEntities = assetsRepository.findByIdIn(assetIds);
        List<NotificationEntity> newNotificationEntities = notPersistedNotifications.stream()
                .map(notification -> toNotificationEntity(investigationEntity, notification, assetEntities))
                .toList();
        notificationRepository.saveAll(newNotificationEntities);
    }

    private void update(NotificationEntity notificationEntity, Notification notification) {
        notificationEntity.setEdcUrl(notification.getEdcUrl());
        notificationEntity.setContractAgreementId(notification.getContractAgreementId());
        notificationEntity.setNotificationReferenceId(notification.getNotificationReferenceId());
        notificationEntity.setTargetDate(notification.getTargetDate());
    }

    private Investigation toInvestigation(InvestigationEntity investigationEntity) {
        List<Notification> notifications = investigationEntity.getNotifications().stream()
                .map(this::toNotification)
                .toList();

        List<String> assetIds = investigationEntity.getAssets().stream()
                .map(AssetEntity::getId)
                .toList();

        return new Investigation(
                new InvestigationId(investigationEntity.getId()),
                new BPN(investigationEntity.getBpn()),
                investigationEntity.getStatus(),
                investigationEntity.getSide(),
                investigationEntity.getCloseReason(),
                investigationEntity.getAcceptReason(),
                investigationEntity.getDeclineReason(),
                investigationEntity.getDescription(),
                investigationEntity.getCreated(),
                assetIds,
                notifications
        );
    }

    private Notification toNotification(NotificationEntity notificationEntity) {
        InvestigationEntity investigation = notificationEntity.getInvestigation();

        return new Notification(
                notificationEntity.getId(),
                notificationEntity.getNotificationReferenceId(),
                notificationEntity.getSenderBpnNumber(),
                notificationEntity.getSenderManufacturerName(),
                notificationEntity.getReceiverBpnNumber(),
                notificationEntity.getReceiverManufacturerName(),
                notificationEntity.getEdcUrl(),
                notificationEntity.getContractAgreementId(),
                investigation.getDescription(),
                investigation.getStatus(),
                notificationEntity.getAssets().stream()
                        .map(asset -> new AffectedPart(asset.getId()))
                        .toList(),
                notificationEntity.getTargetDate(),
                notificationEntity.getSeverity(),
                notificationEntity.getEdcNotificationId(),
                notificationEntity.getCreated(),
                notificationEntity.getUpdated()
        );
    }


    private NotificationEntity toNotificationEntity(InvestigationEntity investigationEntity, Notification notification, List<AssetEntity> investigationAssets) {
        List<AssetEntity> notificationAssets = filterNotificationAssets(notification, investigationAssets);

        if (notificationAssets.isEmpty()) {
            throw new IllegalStateException("Investigation with id %s has no notification assets".formatted(investigationEntity.getId()));
        }

        return new NotificationEntity(
                investigationEntity,
                notification.getSenderBpnNumber(),
                notification.getSenderManufacturerName(),
                notification.getReceiverBpnNumber(),
                notification.getReceiverManufacturerName(),
                notificationAssets,
                notification.getNotificationReferenceId(),
                notification.getTargetDate(),
                notification.getSeverity(),
                notification.getEdcNotificationId(),
                notification.getInvestigationStatus()
        );
    }

    private List<AssetEntity> filterNotificationAssets(Notification notification, List<AssetEntity> assets) {
        Set<String> notificationAffectedAssetIds = notification.getAffectedParts().stream()
                .map(AffectedPart::assetId)
                .collect(Collectors.toSet());

        return assets.stream()
                .filter(it -> notificationAffectedAssetIds.contains(it.getId()))
                .toList();
    }
}
