/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { CaracteristicasPsiquicasDeleteDialogComponent } from 'app/entities/caracteristicas-psiquicas/caracteristicas-psiquicas-delete-dialog.component';
import { CaracteristicasPsiquicasService } from 'app/entities/caracteristicas-psiquicas/caracteristicas-psiquicas.service';

describe('Component Tests', () => {
    describe('CaracteristicasPsiquicas Management Delete Component', () => {
        let comp: CaracteristicasPsiquicasDeleteDialogComponent;
        let fixture: ComponentFixture<CaracteristicasPsiquicasDeleteDialogComponent>;
        let service: CaracteristicasPsiquicasService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [CaracteristicasPsiquicasDeleteDialogComponent]
            })
                .overrideTemplate(CaracteristicasPsiquicasDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CaracteristicasPsiquicasDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CaracteristicasPsiquicasService);
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
