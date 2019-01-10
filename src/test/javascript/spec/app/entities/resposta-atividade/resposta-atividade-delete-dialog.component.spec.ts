/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespostaAtividadeDeleteDialogComponent } from 'app/entities/resposta-atividade/resposta-atividade-delete-dialog.component';
import { RespostaAtividadeService } from 'app/entities/resposta-atividade/resposta-atividade.service';

describe('Component Tests', () => {
    describe('RespostaAtividade Management Delete Component', () => {
        let comp: RespostaAtividadeDeleteDialogComponent;
        let fixture: ComponentFixture<RespostaAtividadeDeleteDialogComponent>;
        let service: RespostaAtividadeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespostaAtividadeDeleteDialogComponent]
            })
                .overrideTemplate(RespostaAtividadeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespostaAtividadeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespostaAtividadeService);
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
