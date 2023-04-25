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

package org.eclipse.tractusx.traceability.infrastructure.jpa.bpn_edc;

import org.eclipse.tractusx.traceability.common.model.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BpnEdcMappingService {

    private final BpnEdcMappingRepository bpnEdcMappingRepository;

    public BpnEdcMappingService(BpnEdcMappingRepository bpnEdcMappingRepository) {
        this.bpnEdcMappingRepository = bpnEdcMappingRepository;
    }

    public PageResult<BpnEdcMapping> getBpnEdcMappings(Pageable pageable) {
        return bpnEdcMappingRepository.findAllPaged(pageable);
    }

    public void createBpnEdcMapping(String bpn, String url) {
        BpnEdcMapping bpnEdcMapping = bpnEdcMappingRepository.findById(bpn);
        if (bpnEdcMapping != null) {
            throw new BpnEdcMappingException("BPN EDC Mapping for BPN: {} already exists.");
        }
        bpnEdcMappingRepository.save(new BpnEdcMappingEntity(bpn, url));
    }

    public void deleteBpnEdcMapping(String bpn) {
        BpnEdcMapping bpnEdcMapping = bpnEdcMappingRepository.findById(bpn);
        if (bpnEdcMapping == null) {
            throw new BpnEdcMappingNotFoundException("Could not find BPN EDC Mapping for BPN " + bpn);
        }
        bpnEdcMappingRepository.deleteById(bpn);
    }

}
