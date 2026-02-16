import { Component, input } from "@angular/core";
import { Transfer } from "../transfer/transfer";

@Component({
    selector: 'bip-card',
    templateUrl: './card.html',
    imports: [Transfer]
})
export class Card {
    title = input('')
    balance = input(0)
    accountId = input(0)

    accounts = input<any>([])

}