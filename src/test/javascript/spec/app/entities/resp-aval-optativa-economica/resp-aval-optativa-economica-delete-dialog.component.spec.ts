/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAvalOptativaEconomicaDeleteDialogComponent } from 'app/entities/resp-aval-optativa-economica/resp-aval-optativa-economica-delete-dialog.component';
import { RespAvalOptativaEconomicaService } from 'app/entities/resp-aval-optativa-economica/resp-aval-optativa-economica.service';

describe('Component Tests', () => {
    describe('RespAvalOptativaEconomica Management Delete Component', () => {
        let comp: RespAvalOptativaEconomicaDeleteDialogComponent;
        let fixture: ComponentFixture<RespAvalOptativaEconomicaDeleteDialogComponent>;
        let service: RespAvalOptativaEconomicaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAvalOptativaEconomicaDeleteDialogComponent]
            })
                .overrideTemplate(RespAvalOptativaEconomicaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespAvalOptativaEconomicaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespAvalOptativaEconomicaService);
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
