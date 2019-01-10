/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { DiaNaoUtilDeleteDialogComponent } from 'app/entities/dia-nao-util/dia-nao-util-delete-dialog.component';
import { DiaNaoUtilService } from 'app/entities/dia-nao-util/dia-nao-util.service';

describe('Component Tests', () => {
    describe('DiaNaoUtil Management Delete Component', () => {
        let comp: DiaNaoUtilDeleteDialogComponent;
        let fixture: ComponentFixture<DiaNaoUtilDeleteDialogComponent>;
        let service: DiaNaoUtilService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DiaNaoUtilDeleteDialogComponent]
            })
                .overrideTemplate(DiaNaoUtilDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DiaNaoUtilDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DiaNaoUtilService);
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
