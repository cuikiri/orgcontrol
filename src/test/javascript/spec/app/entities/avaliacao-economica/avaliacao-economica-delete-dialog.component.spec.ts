/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { AvaliacaoEconomicaDeleteDialogComponent } from 'app/entities/avaliacao-economica/avaliacao-economica-delete-dialog.component';
import { AvaliacaoEconomicaService } from 'app/entities/avaliacao-economica/avaliacao-economica.service';

describe('Component Tests', () => {
    describe('AvaliacaoEconomica Management Delete Component', () => {
        let comp: AvaliacaoEconomicaDeleteDialogComponent;
        let fixture: ComponentFixture<AvaliacaoEconomicaDeleteDialogComponent>;
        let service: AvaliacaoEconomicaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AvaliacaoEconomicaDeleteDialogComponent]
            })
                .overrideTemplate(AvaliacaoEconomicaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AvaliacaoEconomicaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AvaliacaoEconomicaService);
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
