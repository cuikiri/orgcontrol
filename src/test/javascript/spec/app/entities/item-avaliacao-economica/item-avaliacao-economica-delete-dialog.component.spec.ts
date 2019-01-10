/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemAvaliacaoEconomicaDeleteDialogComponent } from 'app/entities/item-avaliacao-economica/item-avaliacao-economica-delete-dialog.component';
import { ItemAvaliacaoEconomicaService } from 'app/entities/item-avaliacao-economica/item-avaliacao-economica.service';

describe('Component Tests', () => {
    describe('ItemAvaliacaoEconomica Management Delete Component', () => {
        let comp: ItemAvaliacaoEconomicaDeleteDialogComponent;
        let fixture: ComponentFixture<ItemAvaliacaoEconomicaDeleteDialogComponent>;
        let service: ItemAvaliacaoEconomicaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemAvaliacaoEconomicaDeleteDialogComponent]
            })
                .overrideTemplate(ItemAvaliacaoEconomicaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemAvaliacaoEconomicaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemAvaliacaoEconomicaService);
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
