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

import { PartResponse, QualityType } from '@page/parts/model/parts.model';
import { otherPartsAssets } from '../otherParts-mock/otherParts.model';

export const mockBmwAssets = [
  {
    id: 'urn:uuid:3583f9fc-8b82-4e4f-9b4a-e0e323dcd9d1',
    idShort: '--',
    batchId: '--',
    nameAtManufacturer: 'Transmission',
    manufacturerPartId: '1O222E8-43',
    partInstanceId: '1O222E8-43',
    manufacturerId: 'BPNL00000003B2OM',
    manufacturerName: 'OEM A',
    nameAtCustomer: 'Transmission',
    customerPartId: '1O222E8-43',
    manufacturingDate: '2022-02-04T13:48:54Z',
    manufacturingCountry: 'JAM',
    specificAssetIds: {},
    childDescriptions: [
      { id: 'urn:uuid:926457b6-a3f3-4c11-b631-a76be2949bc3', idShort: '--' },
      { id: 'urn:uuid:5701ec49-826c-4849-a28d-efb14350884e', idShort: '--' },
      { id: 'urn:uuid:c3bf2039-f835-42e3-8874-227e928fc6cb', idShort: '--' },
    ],
    parentDescriptions: [{ id: 'urn:uuid:55c376ed-e39d-4525-96e6-71f3def7ae41', idShort: '--' }],
    qualityType: 'Ok',
  },
  {
    id: 'urn:uuid:55c376ed-e39d-4525-96e6-71f3def7ae41',
    idShort: '--',
    batchId: '--',
    nameAtManufacturer: 'Vehicle Fully Electric',
    manufacturerPartId: 'PC-30',
    partInstanceId: 'PC-30',
    manufacturerId: 'BPNL00000003AYRE',
    manufacturerName: 'OEM A',
    nameAtCustomer: '--',
    customerPartId: '--',
    manufacturingDate: '2020-07-07T13:07:39Z',
    manufacturingCountry: 'LBR',
    specificAssetIds: {},
    underInvestigation: true,
    childDescriptions: [],
    qualityType: 'Ok',
    van: null,
  },
  {
    id: 'urn:uuid:d37d8fe4-5684-4c31-8c03-f9dbaa48d82b',
    idShort: '--',
    batchId: 'NO-961610245865543350976497',
    nameAtManufacturer: 'Vehicle Combustion',
    manufacturerPartId: 'PS-28',
    partInstanceId: 'PS-28',
    manufacturerId: 'BPNL00000003AYRE',
    manufacturerName: 'OEM A',
    nameAtCustomer: '--',
    customerPartId: '--',
    manufacturingDate: '2017-03-26T20:41:42Z',
    manufacturingCountry: 'GUY',
    specificAssetIds: {},
    childDescriptions: [
      { id: 'urn:uuid:047fbe05-5845-4db3-97b0-731412f0d34f', idShort: '--' },
      { id: 'urn:uuid:ea35cbd9-8a60-410f-9b5e-f5bffeb5a621', idShort: '--' },
    ],
    qualityType: 'Ok',
    van: null,
  },
  {
    id: 'urn:uuid:ab863676-a37c-41e7-a5f9-efaa071dbbf5',
    idShort: '--',
    batchId: '--',
    nameAtManufacturer: 'Vehicle Combustion',
    manufacturerPartId: 'GP-30',
    partInstanceId: 'GP-30',
    manufacturerId: 'BPNL00000003AYRE',
    manufacturerName: 'OEM A',
    nameAtCustomer: '--',
    customerPartId: '--',
    manufacturingDate: '2017-01-26T07:38:24Z',
    manufacturingCountry: 'ESP',
    specificAssetIds: {},
    childDescriptions: [
      { id: 'urn:uuid:e3718167-d20d-41ea-b95b-e42bf45e9fe0', idShort: '--' },
      { id: 'urn:uuid:a75283ca-1761-42c4-9eb4-6aefd15e45d9', idShort: '--' },
    ],
    qualityType: 'Ok',
    van: null,
  },
  {
    id: 'urn:uuid:f4d16a8a-1bff-4d93-9715-2629f3ffa560',
    idShort: '--',
    batchId: '--',
    nameAtManufacturer: 'Vehicle Hybrid',
    manufacturerPartId: 'WL-80',
    partInstanceId: 'WL-80',
    manufacturerId: 'BPNL00000003AYRE',
    manufacturerName: 'OEM A',
    nameAtCustomer: '--',
    customerPartId: '--',
    manufacturingDate: '2019-12-18T10:07:22Z',
    manufacturingCountry: 'CHL',
    specificAssetIds: {},
    childDescriptions: [
      { id: 'urn:uuid:45916acb-c1d2-463a-8e04-0e3590544c91', idShort: '--' },
      { id: 'urn:uuid:9ec81c8e-aafb-4813-9325-3ee163dc8153', idShort: '--' },
      { id: 'urn:uuid:3bcb7695-a99e-47cb-b550-349c294435c4', idShort: '--' },
      { id: 'urn:uuid:7178aca0-99d1-4828-b981-52c59eed4185', idShort: '--' },
      { id: 'urn:uuid:259035d1-27f1-4b84-a627-97ed471082b5', idShort: '--' },
      { id: 'urn:uuid:d8e36564-0d3e-46fc-a985-82691c76b54d', idShort: '--' },
      { id: 'urn:uuid:07d4843f-fb9e-4ae8-9ee8-fd216d4e6595', idShort: '--' },
      { id: 'urn:uuid:fa425abe-5948-4d63-b709-2ab7395ffd22', idShort: '--' },
      { id: 'urn:uuid:3a33ea3f-0186-4dde-a2e8-1811f218403f', idShort: '--' },
      { id: 'urn:uuid:24e61437-87ea-40f4-ae68-dca78ff7ec46', idShort: '--' },
      { id: 'urn:uuid:0f88293e-9a4d-4069-96d1-f27b7d0c95d3', idShort: '--' },
      { id: 'urn:uuid:ef1d0110-6f07-49d6-8722-6164b148d7cc', idShort: '--' },
      { id: 'urn:uuid:28034c01-776b-4e94-b723-39c11f8bde90', idShort: '--' },
      { id: 'urn:uuid:c9940703-f3f9-44e1-8600-72a4ffe0656d', idShort: '--' },
      { id: 'urn:uuid:f9eb489e-7598-430c-99a7-f2cf51960ce9', idShort: '--' },
      { id: 'urn:uuid:54f02bbd-994a-40b3-87da-aab2a3a98bc3', idShort: '--' },
      { id: 'urn:uuid:35f0f41e-fc27-42aa-a997-8a87f0d192d3', idShort: '--' },
      { id: 'urn:uuid:dc8f2863-d82b-47d0-9429-9af0ec0398a6', idShort: '--' },
      { id: 'urn:uuid:ec63091e-257f-4e19-965e-30cfe92532be', idShort: '--' },
      { id: 'urn:uuid:278ef05b-1cdc-4bbb-8fea-e8676e0bdefd', idShort: '--' },
      { id: 'urn:uuid:8bac1134-0153-40b7-b053-d7fa65b2523e', idShort: '--' },
      { id: 'urn:uuid:251027bc-0604-46d3-87c6-876d144e9de2', idShort: '--' },
      { id: 'urn:uuid:290840fc-21c9-4385-b69b-7eb196cd3f25', idShort: '--' },
      { id: 'urn:uuid:0e09c48b-faae-47b2-8096-ae322f8342f4', idShort: '--' },
      { id: 'urn:uuid:2cd64798-f97f-41aa-9cd5-a20c644ba9be', idShort: '--' },
      { id: 'urn:uuid:e9e8f88e-1452-48f6-b241-b646acf3ab16', idShort: '--' },
      { id: 'urn:uuid:ff7ba7f7-bc5d-4f4a-9cae-d4f745d43981', idShort: '--' },
      { id: 'urn:uuid:be1c25c6-44a6-4c47-b90c-223af9048945', idShort: '--' },
      { id: 'urn:uuid:22face63-fd5e-4dc8-b845-29eb7a3feffc', idShort: '--' },
      { id: 'urn:uuid:c0c0efa4-2ed5-454e-8b46-59d87c6b29fa', idShort: '--' },
      { id: 'urn:uuid:05019b87-ae83-4144-9364-2063c8010b36', idShort: '--' },
      { id: 'urn:uuid:3f0d0551-4252-4f2b-8818-f1f5dd49f67e', idShort: '--' },
      { id: 'urn:uuid:e2dcae73-e6f1-45a0-8a8a-5ac7bf5ca338', idShort: '--' },
      { id: 'urn:uuid:70e8e906-d94b-4452-854e-218709d2c0d4', idShort: '--' },
      { id: 'urn:uuid:7509f6b1-586e-4400-b2fc-058667371ae9', idShort: '--' },
    ],
    qualityType: 'Ok',
    van: null,
  },
  {
    id: 'urn:uuid:6853758f-d624-4530-93fb-07487801cb58',
    idShort: '--',
    batchId: '--',
    nameAtManufacturer: 'Vehicle Hybrid',
    manufacturerPartId: 'CM-41',
    partInstanceId: 'CM-41',
    manufacturerId: 'BPNL00000003AYRE',
    manufacturerName: 'OEM A',
    nameAtCustomer: '--',
    customerPartId: '--',
    manufacturingDate: '2012-12-20T19:07:03Z',
    manufacturingCountry: 'MLI',
    specificAssetIds: {},
    childDescriptions: [
      { id: 'urn:uuid:a49be0bf-fc54-441a-87c6-e2da6490d37a', idShort: '--' },
      { id: 'urn:uuid:09ffa07e-2579-4873-a118-81107397f8bf', idShort: '--' },
      { id: 'urn:uuid:df226e89-99ba-470a-93e5-9a9019606fed', idShort: '--' },
      { id: 'urn:uuid:76b0d911-f43f-44cb-a707-d214e5f8aa90', idShort: '--' },
      { id: 'urn:uuid:b4a0eae6-4d11-4c46-9f10-758baf5512cd', idShort: '--' },
      { id: 'urn:uuid:fad0c859-6a64-4495-a6e5-592ef0720495', idShort: '--' },
      { id: 'urn:uuid:e34b5f90-93d9-44ad-bd65-427cf0385aac', idShort: '--' },
      { id: 'urn:uuid:82f9e5ac-322a-4f01-a121-2ce49f92ca30', idShort: '--' },
      { id: 'urn:uuid:63cbaa71-4e57-4d4c-9bd0-82a0fa99d458', idShort: '--' },
      { id: 'urn:uuid:3e49eb5a-f844-4477-a2f7-2ca93c65966b', idShort: '--' },
      { id: 'urn:uuid:4bc260d9-5329-4267-a341-7166370c459d', idShort: '--' },
      { id: 'urn:uuid:0048a01d-53ff-4ec4-951c-62511874f9a1', idShort: '--' },
      { id: 'urn:uuid:5b428bb9-9066-4d78-8578-8177107f2c53', idShort: '--' },
      { id: 'urn:uuid:f5abe887-1607-4f29-ae9f-42c4bc41fba8', idShort: '--' },
      { id: 'urn:uuid:5fb5e5d7-1789-4eb3-889c-e6c0c8b8b2d3', idShort: '--' },
      { id: 'urn:uuid:38f57173-8153-4357-b8b0-ac21b53d2a32', idShort: '--' },
      { id: 'urn:uuid:ecf77f1a-283b-47cc-82c3-8f73efa78911', idShort: '--' },
      { id: 'urn:uuid:227f1cbb-d2e5-4e5b-800d-2bb6d2804954', idShort: '--' },
      { id: 'urn:uuid:af7c69a2-21dc-4dbc-88b5-eef6aaefe094', idShort: '--' },
      { id: 'urn:uuid:f769936b-9ff7-4df2-bf0b-f360ff78a1bf', idShort: '--' },
      { id: 'urn:uuid:3cd59151-add0-4bb3-83dc-9984f323b784', idShort: '--' },
      { id: 'urn:uuid:aaee9f1f-3abb-47dc-9ac6-24051a36064f', idShort: '--' },
      { id: 'urn:uuid:de02909d-5d02-471b-a9f4-6e726fce8fc6', idShort: '--' },
      { id: 'urn:uuid:17e3ee5e-7e0a-42cf-9cad-9473df40dbb2', idShort: '--' },
      { id: 'urn:uuid:f246e043-fb18-4168-9e72-bb3c6d52c425', idShort: '--' },
      { id: 'urn:uuid:faf9dca6-8942-49b5-a13d-fbc2598882d9', idShort: '--' },
      { id: 'urn:uuid:590a1d13-de72-4054-9124-f1ccec3e9a9e', idShort: '--' },
      { id: 'urn:uuid:27e382ba-4d3d-4531-b689-0560217b69d5', idShort: '--' },
      { id: 'urn:uuid:7b93979a-55a7-415d-8ae7-dfead1488f58', idShort: '--' },
      { id: 'urn:uuid:9abb3751-6928-4104-b3a2-0ae3705e301b', idShort: '--' },
      { id: 'urn:uuid:6f7491c9-4101-4524-8d94-46fb4c2247cd', idShort: '--' },
      { id: 'urn:uuid:336cb810-5306-4e0d-9968-8068c1049ec1', idShort: '--' },
      { id: 'urn:uuid:a5555f37-0d63-4104-9443-2e06a892d1e5', idShort: '--' },
      { id: 'urn:uuid:fde07a54-93ba-41cc-b94a-b973c5591d46', idShort: '--' },
      { id: 'urn:uuid:861a1206-4212-4eb4-8d9b-b03ba5334a9e', idShort: '--' },
    ],
    qualityType: 'Ok',
    van: null,
  },
  {
    id: 'urn:uuid:065fe7fd-010a-4175-8564-7c9d8ce7e57e',
    idShort: '--',
    batchId: '--',
    nameAtManufacturer: 'Vehicle Hybrid',
    manufacturerPartId: 'VI-26',
    partInstanceId: 'VI-26',
    manufacturerId: 'BPNL00000003AYRE',
    manufacturerName: 'OEM A',
    nameAtCustomer: '--',
    customerPartId: '--',
    manufacturingDate: '2019-11-18T14:15:21Z',
    manufacturingCountry: 'DEU',
    specificAssetIds: {},
    childDescriptions: [
      { id: 'urn:uuid:14a57c2a-4a57-4216-b898-4484d0c808a7', idShort: '--' },
      { id: 'urn:uuid:f9496bd3-53be-4ad6-99f1-904379782a44', idShort: '--' },
      { id: 'urn:uuid:387b3f5e-c9e8-496d-a50e-d17635eb3996', idShort: '--' },
      { id: 'urn:uuid:0384ab79-ee02-4c97-a325-2aad36da57f8', idShort: '--' },
      { id: 'urn:uuid:7d9411a6-1e7c-4331-8952-e000680740f8', idShort: '--' },
      { id: 'urn:uuid:20d4bc02-2538-4eca-8c8c-bebd98f14f71', idShort: '--' },
      { id: 'urn:uuid:350f3435-a0c3-4f3c-b990-5e75e43a2755', idShort: '--' },
      { id: 'urn:uuid:b91e7822-2a00-45ee-b04a-e6304c523b86', idShort: '--' },
      { id: 'urn:uuid:7970829e-cd93-4668-bd60-e88a90aa1c2b', idShort: '--' },
      { id: 'urn:uuid:86ffe68b-1c34-45a8-bd2c-70ca0caad3ee', idShort: '--' },
      { id: 'urn:uuid:9ecfb916-fdb8-4020-ae9c-52dc5fbfbcdb', idShort: '--' },
      { id: 'urn:uuid:27d42b6b-2c36-45bf-9c6a-6060a9a85e7a', idShort: '--' },
      { id: 'urn:uuid:f484cf31-128d-41a3-985f-d8ae57f79538', idShort: '--' },
      { id: 'urn:uuid:58336956-47bd-44ab-af03-ac4cf3b0c7ea', idShort: '--' },
      { id: 'urn:uuid:e135c91f-1a3c-4193-8125-d553b09f4211', idShort: '--' },
      { id: 'urn:uuid:4d80c6e2-f81e-46ba-a08c-b4ed6790b5d2', idShort: '--' },
      { id: 'urn:uuid:4eb725d7-8c06-4f5b-b36d-6d32d86ccab0', idShort: '--' },
      { id: 'urn:uuid:9f4ec363-9c63-4932-b91d-eabd5584a592', idShort: '--' },
      { id: 'urn:uuid:a1ad3a81-24e9-4855-a37c-4fb72fc47448', idShort: '--' },
      { id: 'urn:uuid:aaf267da-d89d-45af-bcb0-730b0e2524bc', idShort: '--' },
      { id: 'urn:uuid:67bcaaa0-7b58-4e6d-9d0e-5707b2680d9f', idShort: '--' },
      { id: 'urn:uuid:e5cf78d1-a552-4d1f-8d43-ec52807b5f7e', idShort: '--' },
      { id: 'urn:uuid:99837b02-5b55-447e-a81b-9462447242eb', idShort: '--' },
      { id: 'urn:uuid:73ac5cc1-13d7-45ef-ba2b-591e6be41b33', idShort: '--' },
      { id: 'urn:uuid:db060e80-e66a-4929-b5ed-0e0ab6b0d742', idShort: '--' },
      { id: 'urn:uuid:19e2cbcb-df3e-40f5-80cb-49b0dc1afdd8', idShort: '--' },
      { id: 'urn:uuid:f0d170ef-a9fc-43e7-b620-4c4c8320a725', idShort: '--' },
      { id: 'urn:uuid:355a3fe8-a8d6-4b48-9335-58988d152b30', idShort: '--' },
      { id: 'urn:uuid:dc1a3fb7-f290-4e60-b2b5-1e3d349fbd50', idShort: '--' },
      { id: 'urn:uuid:b8bda0b2-6dcd-4cdd-b698-de4a70c784c6', idShort: '--' },
      { id: 'urn:uuid:ec3b6595-3644-4431-b5b4-850ae0a4a623', idShort: '--' },
      { id: 'urn:uuid:e667d272-abc8-41c4-b8b9-031c76e30f6f', idShort: '--' },
      { id: 'urn:uuid:cf4353bd-ff16-4867-ad74-fc065cc8f209', idShort: '--' },
      { id: 'urn:uuid:5f9eecf0-4e26-4f60-936b-34893fc65ca1', idShort: '--' },
      { id: 'urn:uuid:5901e9c2-2c57-458f-8932-de13602d38b7', idShort: '--' },
    ],
    qualityType: 'Ok',
    van: null,
  },
  {
    id: 'urn:uuid:5e5ce920-95ed-434f-b335-efc91b7ce62d',
    idShort: '--',
    batchId: '--',
    nameAtManufacturer: 'Vehicle Combustion',
    manufacturerPartId: 'WQ-59',
    partInstanceId: 'WQ-59',
    manufacturerId: 'BPNL00000003AYRE',
    manufacturerName: 'OEM A',
    nameAtCustomer: '--',
    customerPartId: '--',
    manufacturingDate: '2019-01-08T00:10:37Z',
    manufacturingCountry: 'TON',
    specificAssetIds: {},
    childDescriptions: [
      { id: 'urn:uuid:3f3a1389-4f13-4c54-8780-a49ad61d2a48', idShort: '--' },
      { id: 'urn:uuid:6d64f6ef-123f-4c73-9748-b7e56a5ba72e', idShort: '--' },
    ],
    qualityType: 'Ok',
    van: null,
  },
  {
    id: 'urn:uuid:9a80e5ee-6c27-47f5-912a-56945e26b9a6',
    idShort: '--',
    batchId: '--',
    nameAtManufacturer: 'Vehicle Combustion',
    manufacturerPartId: 'GC-72',
    partInstanceId: 'GC-72',
    manufacturerId: 'BPNL00000003AYRE',
    manufacturerName: 'OEM A',
    nameAtCustomer: '--',
    customerPartId: '--',
    manufacturingDate: '2014-06-12T07:37:00Z',
    manufacturingCountry: 'AND',
    specificAssetIds: {},
    childDescriptions: [
      { id: 'urn:uuid:3583f9fc-8b82-4e4f-9b4a-e0e323dcd9d1', idShort: '--' },
      { id: 'urn:uuid:506f573a-ace2-45a6-bd0b-b1b93e3eca67', idShort: '--' },
    ],
    qualityType: 'Ok',
    van: null,
  },
  {
    id: 'urn:uuid:bf5ae91d-02f8-4e33-b130-90ec402ac0db',
    idShort: '--',
    batchId: '--',
    nameAtManufacturer: 'Vehicle Fully Electric',
    manufacturerPartId: 'VX-55',
    partInstanceId: 'VX-55',
    manufacturerId: 'BPNL00000003AYRE',
    manufacturerName: 'OEM A',
    nameAtCustomer: '--',
    customerPartId: '--',
    manufacturingDate: '2015-01-16T07:52:02Z',
    manufacturingCountry: 'QAT',
    specificAssetIds: {},
    childDescriptions: [
      { id: 'urn:uuid:9444e515-b294-4ec3-b075-710cc2a2594f', idShort: '--' },
      { id: 'urn:uuid:1fcc151a-4e54-465c-936b-9c80d604f4f2', idShort: '--' },
    ],
    qualityType: 'Ok',
    van: null,
  },
  {
    id: 'urn:uuid:d261e0fa-36f5-4128-875e-0f5735f5a535',
    idShort: '--',
    batchId: '--',
    nameAtManufacturer: 'Vehicle Fully Electric',
    manufacturerPartId: 'TV-65',
    partInstanceId: 'TV-65',
    manufacturerId: 'BPNL00000003AYRE',
    manufacturerName: 'OEM A',
    nameAtCustomer: '--',
    customerPartId: '--',
    manufacturingDate: '2019-11-27T10:53:12Z',
    manufacturingCountry: 'KWT',
    specificAssetIds: {},
    childDescriptions: [
      { id: 'urn:uuid:9dc1b6fb-94e7-4911-9e39-abf06c4941d2', idShort: '--' },
      { id: 'urn:uuid:d17bbf68-6cb7-4045-b3ae-67f41403d098', idShort: '--' },
    ],
    qualityType: 'Ok',
    van: null,
  },
] as PartResponse[];

export const mockAssetsCountriesMap: Record<string, number> = {
  NZL: 16,
  DEU: 9,
  PNG: 24,
  STP: 25,
  MHL: 12,
  USA: 13,
  SDN: 17,
  GMB: 14,
  MYS: 16,
  POL: 16,
};

const MockEmptyPart: PartResponse = {
  id: 'urn:uuid:a000a0aa-00a0-0000-000a-0a0000a0a000',
  idShort: '--',
  batchId: '--',
  nameAtManufacturer: 'No Name Article',
  manufacturerPartId: 'AA-00',
  partInstanceId: 'AA-00',
  manufacturerId: 'BPNL00000003AYRE',
  manufacturerName: 'OEM A',
  nameAtCustomer: '--',
  customerPartId: '--',
  manufacturingDate: '2000-01-01T00:00:00Z',
  manufacturingCountry: 'NoName',
  specificAssetIds: {},
  childDescriptions: [],
  qualityType: QualityType.Ok,
  van: null,
};

export const getAssetById = (id: string) => {
  return [...mockBmwAssets, ...otherPartsAssets].find(asset => asset.id === id) || { ...MockEmptyPart, id };
};

export const getRandomAsset = () => {
  const parts = [...mockBmwAssets, ...otherPartsAssets];
  return parts[Math.floor(Math.random() * parts.length)];
};
