import { describe, it, expect, beforeEach, vi } from 'vitest';
import { TestBed, ComponentFixture } from '@angular/core/testing';
import { of } from 'rxjs';
import { Transfer } from './transfer';
import { BipService } from '../../app/services/bip.service';

describe('Transfer', () => {

    let component: Transfer;
    let fixture: ComponentFixture<Transfer>;
    let bipServiceMock: any;

    beforeEach(async () => {

        bipServiceMock = {
            transfer: vi.fn(),
            emitTransfer: vi.fn()
        };

        await TestBed.configureTestingModule({
            imports: [Transfer],
            providers: [
                { provide: BipService, useValue: bipServiceMock }
            ]
        }).compileComponents();

        fixture = TestBed.createComponent(Transfer);
        component = fixture.componentInstance;
    });

    it('should call transfer on success', () => {

        bipServiceMock.transfer.mockReturnValue(of({}));

        fixture.componentRef.setInput('originId', 1);

        component.destinationId.set(2);
        component.amount.set(100);

        component.handleSelected();

        expect(bipServiceMock.transfer).toHaveBeenCalledWith({
            fromId: 1,
            toId: 2,
            amount: 100
        });

        expect(bipServiceMock.emitTransfer).toHaveBeenCalled();
    });

});
