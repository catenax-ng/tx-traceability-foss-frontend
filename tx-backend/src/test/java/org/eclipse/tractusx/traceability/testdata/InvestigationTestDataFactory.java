/********************************************************************************
 * Copyright (c) 2023 Contributors to the Eclipse Foundation
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

package org.eclipse.tractusx.traceability.testdata;

import org.eclipse.tractusx.traceability.common.model.BPN;
import org.eclipse.tractusx.traceability.qualitynotification.domain.base.QualityNotification;
import org.eclipse.tractusx.traceability.qualitynotification.domain.base.QualityNotificationMessage;
import org.eclipse.tractusx.traceability.qualitynotification.domain.base.QualityNotificationSide;
import org.eclipse.tractusx.traceability.qualitynotification.domain.base.QualityNotificationStatus;
import org.eclipse.tractusx.traceability.qualitynotification.domain.investigation.model.AffectedPart;
import org.eclipse.tractusx.traceability.qualitynotification.domain.investigation.model.InvestigationId;
import org.eclipse.tractusx.traceability.qualitynotification.domain.investigation.model.Severity;

import java.time.Instant;
import java.util.List;

public class InvestigationTestDataFactory {
    public static QualityNotification createInvestigationTestData(QualityNotificationStatus investigationStatus, QualityNotificationStatus notificationInvestigationStatus, final String bpnString) {
        InvestigationId investigationId = new InvestigationId(1L);
        BPN bpn = new BPN(bpnString);
        QualityNotificationSide investigationSide = QualityNotificationSide.SENDER;
        String description = "Test Investigation";
        Instant createdAt = Instant.now();
        List<String> assetIds = List.of("asset123", "asset456");

        QualityNotificationMessage notification = QualityNotificationMessage.builder()
                .id("1")
                .notificationReferenceId("notificationId")
                .senderBpnNumber("senderBPN")
                .senderManufacturerName("senderManufacturerName")
                .receiverBpnNumber("recipientBPN")
                .receiverManufacturerName("receiverManufacturerName")
                .edcUrl("senderAddress")
                .contractAgreementId("agreement")
                .description(description)
                .investigationStatus(investigationStatus)
                .affectedParts(List.of(new AffectedPart("part123")))
                .targetDate(Instant.now())
                .severity(Severity.MINOR)
                .edcNotificationId("1")
                .messageId("messageId")
                .isInitial(true)
                .build();
        List<QualityNotificationMessage> notifications = List.of(notification);
/*
		List<Notification> notifications = List.of(new Notification(
                "1",
                "notificationId",
                "senderBPN",
                "senderManufacturerName",
                "recipientBPN",
                "receiverManufacturerName",
                "senderAddress",
                "agreement",
                "Test Notification",
                notificationInvestigationStatus,
                List.of(new AffectedPart("part123")),
                Instant.now(),
                Severity.MINOR,
                "1",
                null,
                null,
                "messageId",
                true
		));*/

        return QualityNotification.builder()
                .investigationId(investigationId)
                .bpn(bpn)
                .investigationStatus(investigationStatus)
                .investigationSide(investigationSide)
                .description(description)
                .createdAt(createdAt)
                .assetIds(assetIds)
                .notifications(notifications)
                .build();
    }


    public static QualityNotification createInvestigationTestDataWithNotificationList(QualityNotificationStatus investigationStatus, String bpnString, List<QualityNotificationMessage> notifications) {
        InvestigationId investigationId = new InvestigationId(1L);
        BPN bpn = new BPN(bpnString);
        QualityNotificationSide investigationSide = QualityNotificationSide.SENDER;
        String description = "Test Investigation";
        Instant createdAt = Instant.now();
        List<String> assetIds = List.of("asset123", "asset456");

        return QualityNotification.builder()
                .investigationId(investigationId)
                .bpn(bpn)
                .investigationStatus(investigationStatus)
                .investigationSide(investigationSide)
                .description(description)
                .createdAt(createdAt)
                .assetIds(assetIds)
                .notifications(notifications)
                .build();
    }

    public static QualityNotification createInvestigationTestData(QualityNotificationStatus investigationStatus, QualityNotificationStatus notificationInvestigationStatus) {
        InvestigationId investigationId = new InvestigationId(1L);
        BPN bpn = new BPN("bpn123");
        QualityNotificationSide investigationSide = QualityNotificationSide.SENDER;
        String description = "Test Investigation";
        Instant createdAt = Instant.now();
        List<String> assetIds = List.of("asset123", "asset456");

        QualityNotificationMessage notification = QualityNotificationMessage.builder()
                .id("1")
                .notificationReferenceId("notificationId")
                .senderBpnNumber("senderBPN")
                .senderManufacturerName("senderManufacturerName")
                .receiverBpnNumber("recipientBPN")
                .receiverManufacturerName("receiverManufacturerName")
                .edcUrl("senderAddress")
                .contractAgreementId("agreement")
                .description(description)
                .investigationStatus(notificationInvestigationStatus)
                .affectedParts(List.of(new AffectedPart("part123")))
                .severity(Severity.MINOR)
                .edcNotificationId("123")
                .messageId("messageId")
                .isInitial(true)
                .build();
        List<QualityNotificationMessage> notifications = List.of(notification);

/*		List<Notification> notifications = List.of(new Notification(
                "1",
                "notificationId",
                "senderBPN",
                "senderManufacturerName",
                "recipientBPN",
                "receiverManufacturerName",
                "senderAddress",
                "agreement",
                "Test Notification",
                notificationInvestigationStatus,
                List.of(new AffectedPart("part123")),
                Instant.now(),
                Severity.MINOR,
                "123",
                null,
                null,
                "messageId",
                true
		));*/

        return QualityNotification.builder()
                .investigationId(investigationId)
                .bpn(bpn)
                .investigationStatus(investigationStatus)
                .investigationSide(investigationSide)
                .description(description)
                .createdAt(createdAt)
                .assetIds(assetIds)
                .notifications(notifications)
                .build();
    }

    public static QualityNotification createInvestigationTestData(QualityNotificationSide investigationSide) {
        InvestigationId investigationId = new InvestigationId(1L);
        BPN bpn = new BPN("bpn123");
        String closeReason = null;
        String acceptReason = null;
        String declineReason = null;
        String description = "Test Investigation";
        Instant createdAt = Instant.now();
        List<String> assetIds = List.of("asset123", "asset456");


        QualityNotificationMessage notification = QualityNotificationMessage.builder()
                .id("1")
                .notificationReferenceId("notificationId")
                .senderBpnNumber("senderBPN")
                .senderManufacturerName("senderManufacturerName")
                .receiverBpnNumber("recipientBPN")
                .receiverManufacturerName("receiverManufacturerName")
                .edcUrl("senderAddress")
                .contractAgreementId("agreement")
                .description(description)
                .investigationStatus(QualityNotificationStatus.ACKNOWLEDGED)
                .affectedParts(List.of(new AffectedPart("part123")))
                .severity(Severity.MINOR)
                .edcNotificationId("123")
                .messageId("messageId")
                .isInitial(true)
                .build();
        List<QualityNotificationMessage> notifications = List.of(notification);

/*        List<Notification> notifications = List.of(new Notification(
                "1",
                "notificationId",
                "senderBPN",
                "senderManufacturerName",
                "recipientBPN",
                "receiverManufacturerName",
                "senderAddress",
                "agreement",
                "Test Notification",
                InvestigationStatus.ACKNOWLEDGED,
                List.of(new AffectedPart("part123")),
                Instant.now(),
                Severity.MINOR,
                "123",
                null,
                null,
                "messageId",
                true
        ));*/

        return QualityNotification.builder()
                .investigationId(investigationId)
                .bpn(bpn)
                .investigationStatus(QualityNotificationStatus.ACKNOWLEDGED)
                .investigationSide(investigationSide)
                .description(description)
                .createdAt(createdAt)
                .assetIds(assetIds)
                .notifications(notifications)
                .build();
    }
}
