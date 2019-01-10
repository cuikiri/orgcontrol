/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { MotivoDiaNaoUtilDeleteDialogComponent } from 'app/entities/motivo-dia-nao-util/motivo-dia-nao-util-delete-dialog.component';
import { MotivoDiaNaoUtilService } from 'app/entities/motivo-dia-nao-util/motivo-dia-nao-util.service';

describe('Component Tests', () => {
    describe('MotivoDiaNaoUtil Management Delete Component', () => {
        let comp: MotivoDiaNaoUtilDeleteDialogComponent;
        let fixture: ComponentFixture<MotivoDiaNaoUtilDeleteDialogComponent>;
        let service: MotivoDiaNaoUtilService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [MotivoDiaNaoUtilDeleteDialogComponent]
            })
                .overrideTemplate(MotivoDiaNaoUtilDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MotivoDiaNaoUtilDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MotivoDiaNaoUtilService);
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
