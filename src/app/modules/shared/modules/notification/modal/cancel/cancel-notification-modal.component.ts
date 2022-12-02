/********************************************************************************
 * Copyright (c) 2021,2022 Contributors to the CatenaX (ng) GitHub Organisation
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

import { Component, EventEmitter, Input, Output, TemplateRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastService } from '@shared/components/toasts/toast.service';
import { Notification } from '@shared/model/notification.model';
import { ModalData } from '@shared/modules/modal/core/modal.model';
import { ModalService } from '@shared/modules/modal/core/modal.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-cancel-notification-modal',
  templateUrl: './cancel-notification-modal.component.html',
})
export class CancelNotificationModalComponent {
  @ViewChild('Modal') modal: TemplateRef<unknown>;
  @Input() cancelCall: (id: string) => Observable<void>;
  @Output() confirmActionCompleted = new EventEmitter<void>();

  public notification: Notification;
  public readonly formGroup;
  private readonly textAreaControl = new FormControl();

  constructor(private readonly toastService: ToastService, private readonly confirmModalService: ModalService) {
    this.formGroup = new FormGroup({ notificationId: this.textAreaControl });
  }

  public show(notification: any): void {
    this.notification = notification;
    this.textAreaControl.setValidators([Validators.required, Validators.pattern(this.notification.id.toString())]);
    const onConfirm = (isConfirmed: boolean) => {
      this.formGroup.reset();
      if (!isConfirmed) return;

      this.cancelCall(notification.id).subscribe({
        next: () => {
          this.toastService.success('commonInvestigation.modal.successfullyCanceled');
          this.confirmActionCompleted.emit();
        },
        error: () => {
          this.toastService.error('commonInvestigation.modal.failedCancel');
        },
      });
    };

    const options: ModalData = {
      title: 'commonInvestigation.modal.cancellationTitle',
      buttonRight: 'commonInvestigation.modal.cancellationConfirm',
      buttonLeft: 'commonInvestigation.modal.cancel',
      primaryButtonColour: 'warn',

      template: this.modal,
      formGroup: this.formGroup,
      onConfirm,
    };

    this.confirmModalService.open(options);
  }
}
