/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { ExameMedicoDeleteDialogComponent } from 'app/entities/exame-medico/exame-medico-delete-dialog.component';
import { ExameMedicoService } from 'app/entities/exame-medico/exame-medico.service';

describe('Component Tests', () => {
    describe('ExameMedico Management Delete Component', () => {
        let comp: ExameMedicoDeleteDialogComponent;
        let fixture: ComponentFixture<ExameMedicoDeleteDialogComponent>;
        let service: ExameMedicoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ExameMedicoDeleteDialogComponent]
            })
                .overrideTemplate(ExameMedicoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExameMedicoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExameMedicoService);
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
