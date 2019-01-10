/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoBiotipoDeleteDialogComponent } from 'app/entities/tipo-biotipo/tipo-biotipo-delete-dialog.component';
import { TipoBiotipoService } from 'app/entities/tipo-biotipo/tipo-biotipo.service';

describe('Component Tests', () => {
    describe('TipoBiotipo Management Delete Component', () => {
        let comp: TipoBiotipoDeleteDialogComponent;
        let fixture: ComponentFixture<TipoBiotipoDeleteDialogComponent>;
        let service: TipoBiotipoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoBiotipoDeleteDialogComponent]
            })
                .overrideTemplate(TipoBiotipoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoBiotipoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoBiotipoService);
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
