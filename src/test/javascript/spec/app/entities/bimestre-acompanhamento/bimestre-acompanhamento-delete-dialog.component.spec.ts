/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { BimestreAcompanhamentoDeleteDialogComponent } from 'app/entities/bimestre-acompanhamento/bimestre-acompanhamento-delete-dialog.component';
import { BimestreAcompanhamentoService } from 'app/entities/bimestre-acompanhamento/bimestre-acompanhamento.service';

describe('Component Tests', () => {
    describe('BimestreAcompanhamento Management Delete Component', () => {
        let comp: BimestreAcompanhamentoDeleteDialogComponent;
        let fixture: ComponentFixture<BimestreAcompanhamentoDeleteDialogComponent>;
        let service: BimestreAcompanhamentoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [BimestreAcompanhamentoDeleteDialogComponent]
            })
                .overrideTemplate(BimestreAcompanhamentoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BimestreAcompanhamentoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BimestreAcompanhamentoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
