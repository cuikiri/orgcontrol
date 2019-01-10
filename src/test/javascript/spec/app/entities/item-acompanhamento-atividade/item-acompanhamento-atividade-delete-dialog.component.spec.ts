/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemAcompanhamentoAtividadeDeleteDialogComponent } from 'app/entities/item-acompanhamento-atividade/item-acompanhamento-atividade-delete-dialog.component';
import { ItemAcompanhamentoAtividadeService } from 'app/entities/item-acompanhamento-atividade/item-acompanhamento-atividade.service';

describe('Component Tests', () => {
    describe('ItemAcompanhamentoAtividade Management Delete Component', () => {
        let comp: ItemAcompanhamentoAtividadeDeleteDialogComponent;
        let fixture: ComponentFixture<ItemAcompanhamentoAtividadeDeleteDialogComponent>;
        let service: ItemAcompanhamentoAtividadeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemAcompanhamentoAtividadeDeleteDialogComponent]
            })
                .overrideTemplate(ItemAcompanhamentoAtividadeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemAcompanhamentoAtividadeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemAcompanhamentoAtividadeService);
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
