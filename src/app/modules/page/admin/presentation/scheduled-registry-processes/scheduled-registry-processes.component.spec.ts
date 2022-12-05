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

import { AdminModule } from '@page/admin/admin.module';
import { screen, waitFor } from '@testing-library/angular';
import { server } from '@tests/mock-test-server';
import { renderComponent } from '@tests/test-render.utils';
import { ScheduledRegistryProcessesComponent } from './scheduled-registry-processes.component';

describe('ScheduledRegistryProcessesComponent', () => {
  beforeAll(() => server.start({ quiet: true, onUnhandledRequest: 'bypass' }));
  afterEach(() => server.resetHandlers());
  afterAll(() => server.stop());

  const renderMap = () =>
    renderComponent(ScheduledRegistryProcessesComponent, {
      imports: [AdminModule],
      translations: ['page.admin'],
    });

  it('should create', async () => {
    await renderMap();

    expect(await screen.getByText('Registry lookups')).toBeInTheDocument();
  });

  it('should render data', async () => {
    await renderMap();
    await waitFor(() => expect(screen.getByText('5/1/2022')).toBeInTheDocument());
  });
});
