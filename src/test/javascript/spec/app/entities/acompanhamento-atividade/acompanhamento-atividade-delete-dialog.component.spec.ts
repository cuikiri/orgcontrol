/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { AcompanhamentoAtividadeDeleteDialogComponent } from 'app/entities/acompanhamento-atividade/acompanhamento-atividade-delete-dialog.component';
import { AcompanhamentoAtividadeService } from 'app/entities/acompanhamento-atividade/acompanhamento-atividade.service';

describe('Component Tests', () => {
    describe('AcompanhamentoAtividade Management Delete Component', () => {
        let comp: AcompanhamentoAtividadeDeleteDialogComponent;
        let fixture: ComponentFixture<AcompanhamentoAtividadeDeleteDialogComponent>;
        let service: AcompanhamentoAtividadeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AcompanhamentoAtividadeDeleteDialogComponent]
            })
                .overrideTemplate(AcompanhamentoAtividadeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AcompanhamentoAtividadeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AcompanhamentoAtividadeService);
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
