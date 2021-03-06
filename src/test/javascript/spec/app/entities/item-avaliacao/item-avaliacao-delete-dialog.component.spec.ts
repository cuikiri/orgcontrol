/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemAvaliacaoDeleteDialogComponent } from 'app/entities/item-avaliacao/item-avaliacao-delete-dialog.component';
import { ItemAvaliacaoService } from 'app/entities/item-avaliacao/item-avaliacao.service';

describe('Component Tests', () => {
    describe('ItemAvaliacao Management Delete Component', () => {
        let comp: ItemAvaliacaoDeleteDialogComponent;
        let fixture: ComponentFixture<ItemAvaliacaoDeleteDialogComponent>;
        let service: ItemAvaliacaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemAvaliacaoDeleteDialogComponent]
            })
                .overrideTemplate(ItemAvaliacaoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemAvaliacaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemAvaliacaoService);
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
