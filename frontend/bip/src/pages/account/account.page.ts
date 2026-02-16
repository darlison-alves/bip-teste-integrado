import { Component, signal } from "@angular/core";
import { Card } from "../../components/card/card";
import { BipService } from "../../app/services/bip.service";

@Component({
    selector: 'account-page',
    templateUrl: 'account.page.html',
    imports: [Card]
})
export class AccountPage {

    accounts = signal<any>([])

    constructor(private api:BipService) {}

    ngOnInit() {        
        this.api.destino$.subscribe(dest => {
            this.loadList()
        })
    }

    loadList() {
        this.api.getList().subscribe( data => this.accounts.set(data))
    }

}