/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAcompanhamentoAtividadeDeleteDialogComponent } from 'app/entities/tipo-acompanhamento-atividade/tipo-acompanhamento-atividade-delete-dialog.component';
import { TipoAcompanhamentoAtividadeService } from 'app/entities/tipo-acompanhamento-atividade/tipo-acompanhamento-atividade.service';

describe('Component Tests', () => {
    describe('TipoAcompanhamentoAtividade Management Delete Component', () => {
        let comp: TipoAcompanhamentoAtividadeDeleteDialogComponent;
        let fixture: ComponentFixture<TipoAcompanhamentoAtividadeDeleteDialogComponent>;
        let service: TipoAcompanhamentoAtividadeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAcompanhamentoAtividadeDeleteDialogComponent]
            })
                .overrideTemplate(TipoAcompanhamentoAtividadeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoAcompanhamentoAtividadeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoAcompanhamentoAtividadeService);
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
