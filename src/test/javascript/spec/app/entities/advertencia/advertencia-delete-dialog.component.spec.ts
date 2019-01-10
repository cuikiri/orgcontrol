/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { AdvertenciaDeleteDialogComponent } from 'app/entities/advertencia/advertencia-delete-dialog.component';
import { AdvertenciaService } from 'app/entities/advertencia/advertencia.service';

describe('Component Tests', () => {
    describe('Advertencia Management Delete Component', () => {
        let comp: AdvertenciaDeleteDialogComponent;
        let fixture: ComponentFixture<AdvertenciaDeleteDialogComponent>;
        let service: AdvertenciaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AdvertenciaDeleteDialogComponent]
            })
                .overrideTemplate(AdvertenciaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdvertenciaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdvertenciaService);
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
