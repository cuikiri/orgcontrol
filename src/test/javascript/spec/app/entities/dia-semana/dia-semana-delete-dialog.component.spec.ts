/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { DiaSemanaDeleteDialogComponent } from 'app/entities/dia-semana/dia-semana-delete-dialog.component';
import { DiaSemanaService } from 'app/entities/dia-semana/dia-semana.service';

describe('Component Tests', () => {
    describe('DiaSemana Management Delete Component', () => {
        let comp: DiaSemanaDeleteDialogComponent;
        let fixture: ComponentFixture<DiaSemanaDeleteDialogComponent>;
        let service: DiaSemanaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DiaSemanaDeleteDialogComponent]
            })
                .overrideTemplate(DiaSemanaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DiaSemanaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DiaSemanaService);
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
