import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { TransferPayload } from "../models";

@Injectable({
    providedIn: 'root'
})
export class BipService {

    private urlBase = 'http://localhost:8080'
    private transferSubjects = new BehaviorSubject<string>('');
    private transferCompletedSubjects = new BehaviorSubject<string>('');

    destino$ = this.transferSubjects.asObservable();
    destCompletedTransfer$ = this.transferCompletedSubjects.asObservable();

    constructor(private http: HttpClient) { }

    getList() {
        return this.http.get(`${this.urlBase}/api/v1/beneficios`)
    }

    emitTransfer(payload: any) {
        return this.transferSubjects.next(payload);
    }

    emitFinishTransfer(payload: any) {
        this.transferCompletedSubjects.next(payload);
    }

    transfer(payload: TransferPayload) {
        return this.http.post(`${this.urlBase}/api/v1/beneficios/transfer`, payload)
    }

}