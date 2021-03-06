/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { PeriodoAtividadeDeleteDialogComponent } from 'app/entities/periodo-atividade/periodo-atividade-delete-dialog.component';
import { PeriodoAtividadeService } from 'app/entities/periodo-atividade/periodo-atividade.service';

describe('Component Tests', () => {
    describe('PeriodoAtividade Management Delete Component', () => {
        let comp: PeriodoAtividadeDeleteDialogComponent;
        let fixture: ComponentFixture<PeriodoAtividadeDeleteDialogComponent>;
        let service: PeriodoAtividadeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PeriodoAtividadeDeleteDialogComponent]
            })
                .overrideTemplate(PeriodoAtividadeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PeriodoAtividadeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PeriodoAtividadeService);
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
