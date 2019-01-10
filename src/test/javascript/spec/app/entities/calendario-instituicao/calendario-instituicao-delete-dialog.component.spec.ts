/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { CalendarioInstituicaoDeleteDialogComponent } from 'app/entities/calendario-instituicao/calendario-instituicao-delete-dialog.component';
import { CalendarioInstituicaoService } from 'app/entities/calendario-instituicao/calendario-instituicao.service';

describe('Component Tests', () => {
    describe('CalendarioInstituicao Management Delete Component', () => {
        let comp: CalendarioInstituicaoDeleteDialogComponent;
        let fixture: ComponentFixture<CalendarioInstituicaoDeleteDialogComponent>;
        let service: CalendarioInstituicaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [CalendarioInstituicaoDeleteDialogComponent]
            })
                .overrideTemplate(CalendarioInstituicaoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CalendarioInstituicaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CalendarioInstituicaoService);
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
