/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemPlanejamentoEnsinoDeleteDialogComponent } from 'app/entities/item-planejamento-ensino/item-planejamento-ensino-delete-dialog.component';
import { ItemPlanejamentoEnsinoService } from 'app/entities/item-planejamento-ensino/item-planejamento-ensino.service';

describe('Component Tests', () => {
    describe('ItemPlanejamentoEnsino Management Delete Component', () => {
        let comp: ItemPlanejamentoEnsinoDeleteDialogComponent;
        let fixture: ComponentFixture<ItemPlanejamentoEnsinoDeleteDialogComponent>;
        let service: ItemPlanejamentoEnsinoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemPlanejamentoEnsinoDeleteDialogComponent]
            })
                .overrideTemplate(ItemPlanejamentoEnsinoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemPlanejamentoEnsinoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemPlanejamentoEnsinoService);
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
