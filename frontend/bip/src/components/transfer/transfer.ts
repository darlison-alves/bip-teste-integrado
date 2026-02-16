import { Component, input, output, signal } from "@angular/core";
import { TransferActionViewEnum } from "../../app/enums/transfer.enum";
import { BipService } from "../../app/services/bip.service";
import { TransferDTO, TransferPayload } from "../../app/models";

@Component({
    selector: 'b-transfer',
    templateUrl: './transfer.html',
})
export class Transfer {

    originId = input(0)
    options = input<TransferDTO[]>([])

    destinationId = signal(0)
    amount = signal(0)

    success = signal('')
    error = signal('')
    loading = signal(false)

    constructor(private api: BipService) { }

    view = signal(TransferActionViewEnum.BUTTON)

    viewedButton() {
        return this.view() == TransferActionViewEnum.BUTTON
    }

    viewedForm() {
        return this.view() == TransferActionViewEnum.FORM
    }

    handleViewTransfer() {
        this.view.update(() => TransferActionViewEnum.FORM);
    }


    handleViewTransferCancel() {
        this.view.update(() => TransferActionViewEnum.BUTTON);
    }

    handleDestination(event: Event) {
        const selectElement = event.target as HTMLSelectElement;
        this.destinationId.set(Number(selectElement.value))
    }

    handleAmount(event: Event) {
        const selectElement = event.target as HTMLSelectElement;
        this.amount.set(Number(selectElement.value))
    }

    handleSelected() {

        this.loading.set(true)
        this.error.set('')

        this.api.transfer({
            fromId: this.originId(),
            toId: this.destinationId(),
            amount: this.amount()
        }).subscribe({
            next: payload => this.api.emitTransfer(payload),
            complete: () => {
                console.log('[completed]')
                this.loading.set(false)
            },
            error: (err) => {
                console.error('[error]', err)
                this.loading.set(false)
                this.error.set(err.error.message)
            },
        })
    }

}