/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { PlanejamentoAtividadeDeleteDialogComponent } from 'app/entities/planejamento-atividade/planejamento-atividade-delete-dialog.component';
import { PlanejamentoAtividadeService } from 'app/entities/planejamento-atividade/planejamento-atividade.service';

describe('Component Tests', () => {
    describe('PlanejamentoAtividade Management Delete Component', () => {
        let comp: PlanejamentoAtividadeDeleteDialogComponent;
        let fixture: ComponentFixture<PlanejamentoAtividadeDeleteDialogComponent>;
        let service: PlanejamentoAtividadeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PlanejamentoAtividadeDeleteDialogComponent]
            })
                .overrideTemplate(PlanejamentoAtividadeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanejamentoAtividadeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanejamentoAtividadeService);
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
