/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { PeriodoDuracaoDeleteDialogComponent } from 'app/entities/periodo-duracao/periodo-duracao-delete-dialog.component';
import { PeriodoDuracaoService } from 'app/entities/periodo-duracao/periodo-duracao.service';

describe('Component Tests', () => {
    describe('PeriodoDuracao Management Delete Component', () => {
        let comp: PeriodoDuracaoDeleteDialogComponent;
        let fixture: ComponentFixture<PeriodoDuracaoDeleteDialogComponent>;
        let service: PeriodoDuracaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PeriodoDuracaoDeleteDialogComponent]
            })
                .overrideTemplate(PeriodoDuracaoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PeriodoDuracaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PeriodoDuracaoService);
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
