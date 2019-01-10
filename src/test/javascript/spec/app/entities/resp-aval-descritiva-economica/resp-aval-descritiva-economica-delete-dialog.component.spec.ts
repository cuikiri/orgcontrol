/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAvalDescritivaEconomicaDeleteDialogComponent } from 'app/entities/resp-aval-descritiva-economica/resp-aval-descritiva-economica-delete-dialog.component';
import { RespAvalDescritivaEconomicaService } from 'app/entities/resp-aval-descritiva-economica/resp-aval-descritiva-economica.service';

describe('Component Tests', () => {
    describe('RespAvalDescritivaEconomica Management Delete Component', () => {
        let comp: RespAvalDescritivaEconomicaDeleteDialogComponent;
        let fixture: ComponentFixture<RespAvalDescritivaEconomicaDeleteDialogComponent>;
        let service: RespAvalDescritivaEconomicaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAvalDescritivaEconomicaDeleteDialogComponent]
            })
                .overrideTemplate(RespAvalDescritivaEconomicaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespAvalDescritivaEconomicaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespAvalDescritivaEconomicaService);
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
