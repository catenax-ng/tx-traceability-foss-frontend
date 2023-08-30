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
package org.eclipse.tractusx.traceability.infrastructure.edc.blackbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import org.eclipse.edc.spi.types.domain.edr.EndpointDataReference;
import org.eclipse.tractusx.irs.edc.client.ContractNegotiationService;
import org.eclipse.tractusx.irs.edc.client.EDCCatalogFacade;
import org.eclipse.tractusx.irs.edc.client.EndpointDataReferenceStorage;
import org.eclipse.tractusx.irs.edc.client.exceptions.ContractNegotiationException;
import org.eclipse.tractusx.irs.edc.client.exceptions.TransferProcessException;
import org.eclipse.tractusx.irs.edc.client.exceptions.UsagePolicyException;
import org.eclipse.tractusx.irs.edc.client.model.CatalogItem;
import org.eclipse.tractusx.irs.edc.client.model.NegotiationResponse;
import org.eclipse.tractusx.irs.edc.client.policy.PolicyCheckerService;
import org.eclipse.tractusx.traceability.infrastructure.edc.properties.EdcProperties;
import org.eclipse.tractusx.traceability.qualitynotification.domain.model.QualityNotificationMessage;
import org.eclipse.tractusx.traceability.qualitynotification.domain.model.QualityNotificationStatus;
import org.eclipse.tractusx.traceability.qualitynotification.domain.model.QualityNotificationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvestigationsEDCFacadeTest {
    @Mock
    HttpCallService httpCallService;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    EdcProperties edcProperties;
    @Mock
    EDCCatalogFacade edcCatalogFacade;
    @Mock
    ContractNegotiationService contractNegotiationService;
    @Mock
    EndpointDataReferenceStorage endpointDataReferenceStorage;
    @Mock
    PolicyCheckerService policyCheckerService;
    @Mock
    EndpointDataReference endpointDataReference;
    @InjectMocks
    InvestigationsEDCFacade investigationsEDCFacade;

    @Test
    void givenCorrectInvestigationMessage_whenStartEdcTransferNew_thenSendIt() throws TransferProcessException, UsagePolicyException, ContractNegotiationException, IOException {
        // given
        final String receiverEdcUrl = "https://receiver.com";
        final String senderEdcUrl = "https://sender.com";
        final String agreementId = "negotiationId";
        final String dataReferenceEndpoint = "https://endpoint.com";
        final QualityNotificationMessage notificationMessage = QualityNotificationMessage.builder()
                .type(QualityNotificationType.INVESTIGATION)
                .notificationStatus(QualityNotificationStatus.CREATED)
                .isInitial(true)
                .build();
        final CatalogItem catalogItem = CatalogItem.builder()
                .build();
        final String idsPath = "/api/v1/dsp";
        when(edcProperties.getIdsPath()).thenReturn(idsPath);
        when(edcCatalogFacade.fetchCatalogItems(any())).thenReturn(List.of(catalogItem));
        when(policyCheckerService.isValid(null)).thenReturn(true);
        when(contractNegotiationService.negotiate(receiverEdcUrl + idsPath, catalogItem))
                .thenReturn(NegotiationResponse.builder().contractAgreementId(agreementId).build());
        when(endpointDataReference.getEndpoint()).thenReturn("endpoint");
        when(endpointDataReference.getAuthCode()).thenReturn("authCode");
        when(endpointDataReference.getAuthKey()).thenReturn("authKey");
        when(endpointDataReference.getEndpoint()).thenReturn(dataReferenceEndpoint);
        when(httpCallService.getUrl(dataReferenceEndpoint, null, null)).thenReturn(HttpUrl.parse(dataReferenceEndpoint));
        when(endpointDataReferenceStorage.remove(agreementId)).thenReturn(Optional.ofNullable(endpointDataReference));
        when(objectMapper.writeValueAsString(any())).thenReturn("{body}");

        // when
        investigationsEDCFacade.startEdcTransferNew(notificationMessage, receiverEdcUrl, senderEdcUrl);

        // then
        verify(httpCallService).sendRequest(any());
    }

    @Test
    void givenCorrectInvestigationMessageButSendRequestThrowsException_whenStartEdcTransferNew_thenThrowSendNotificationException() throws TransferProcessException, UsagePolicyException, ContractNegotiationException, IOException {
        // given
        final String receiverEdcUrl = "https://receiver.com";
        final String senderEdcUrl = "https://sender.com";
        final String agreementId = "negotiationId";
        final String dataReferenceEndpoint = "https://endpoint.com";
        final QualityNotificationMessage notificationMessage = QualityNotificationMessage.builder()
                .type(QualityNotificationType.INVESTIGATION)
                .notificationStatus(QualityNotificationStatus.CREATED)
                .isInitial(true)
                .build();
        final CatalogItem catalogItem = CatalogItem.builder()
                .build();
        final String idsPath = "/api/v1/dsp";
        when(edcProperties.getIdsPath()).thenReturn(idsPath);
        when(edcCatalogFacade.fetchCatalogItems(any())).thenReturn(List.of(catalogItem));
        when(policyCheckerService.isValid(null)).thenReturn(true);
        when(contractNegotiationService.negotiate(receiverEdcUrl + idsPath, catalogItem))
                .thenReturn(NegotiationResponse.builder().contractAgreementId(agreementId).build());
        when(endpointDataReference.getEndpoint()).thenReturn("endpoint");
        when(endpointDataReference.getAuthCode()).thenReturn("authCode");
        when(endpointDataReference.getAuthKey()).thenReturn("authKey");
        when(endpointDataReference.getEndpoint()).thenReturn(dataReferenceEndpoint);
        when(httpCallService.getUrl(dataReferenceEndpoint, null, null)).thenReturn(HttpUrl.parse(dataReferenceEndpoint));
        when(endpointDataReferenceStorage.remove(agreementId)).thenReturn(Optional.ofNullable(endpointDataReference));
        when(objectMapper.writeValueAsString(any())).thenReturn("{body}");
        doThrow(new RuntimeException()).when(httpCallService).sendRequest(any());

        // when/then
        assertThrows(SendNotificationException.class, () -> investigationsEDCFacade.startEdcTransferNew(notificationMessage, receiverEdcUrl, senderEdcUrl));
    }

    @Test
    void givenCorrectInvestigationMessageButNegotiateContractAgreementHasNoCatalogItem_whenStartEdcTransferNew_thenThrowContractNegotiationException() throws TransferProcessException, UsagePolicyException, ContractNegotiationException, IOException {
        // given
        final String receiverEdcUrl = "https://receiver.com";
        final String senderEdcUrl = "https://sender.com";
        final QualityNotificationMessage notificationMessage = QualityNotificationMessage.builder()
                .type(QualityNotificationType.INVESTIGATION)
                .notificationStatus(QualityNotificationStatus.CREATED)
                .isInitial(true)
                .build();
        final CatalogItem catalogItem = CatalogItem.builder()
                .build();
        final String idsPath = "/api/v1/dsp";
        when(edcProperties.getIdsPath()).thenReturn(idsPath);
        when(edcCatalogFacade.fetchCatalogItems(any())).thenReturn(List.of(catalogItem));
        when(policyCheckerService.isValid(null)).thenReturn(true);
        when(contractNegotiationService.negotiate(receiverEdcUrl + idsPath, catalogItem))
                .thenReturn(null);

        // when/then
        assertThrows(ContractNegotiationException.class, () -> investigationsEDCFacade.startEdcTransferNew(notificationMessage, receiverEdcUrl, senderEdcUrl));
    }

    @Test
    void givenCorrectInvestigationMessageButCatalogItem_whenStartEdcTransferNew_thenThrowSendNotificationException() throws TransferProcessException, UsagePolicyException, ContractNegotiationException, IOException {
        // given
        final String receiverEdcUrl = "https://receiver.com";
        final String senderEdcUrl = "https://sender.com";
        final QualityNotificationMessage notificationMessage = QualityNotificationMessage.builder()
                .type(QualityNotificationType.INVESTIGATION)
                .notificationStatus(QualityNotificationStatus.CREATED)
                .isInitial(true)
                .build();
        final String idsPath = "/api/v1/dsp";
        when(edcProperties.getIdsPath()).thenReturn(idsPath);
        when(edcCatalogFacade.fetchCatalogItems(any())).thenReturn(List.of());

        // when/then
        assertThrows(NoCatalogItemException.class, () -> investigationsEDCFacade.startEdcTransferNew(notificationMessage, receiverEdcUrl, senderEdcUrl));
    }
}
