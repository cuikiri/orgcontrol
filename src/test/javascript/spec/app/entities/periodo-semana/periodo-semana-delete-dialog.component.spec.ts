/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { PeriodoSemanaDeleteDialogComponent } from 'app/entities/periodo-semana/periodo-semana-delete-dialog.component';
import { PeriodoSemanaService } from 'app/entities/periodo-semana/periodo-semana.service';

describe('Component Tests', () => {
    describe('PeriodoSemana Management Delete Component', () => {
        let comp: PeriodoSemanaDeleteDialogComponent;
        let fixture: ComponentFixture<PeriodoSemanaDeleteDialogComponent>;
        let service: PeriodoSemanaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PeriodoSemanaDeleteDialogComponent]
            })
                .overrideTemplate(PeriodoSemanaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PeriodoSemanaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PeriodoSemanaService);
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
