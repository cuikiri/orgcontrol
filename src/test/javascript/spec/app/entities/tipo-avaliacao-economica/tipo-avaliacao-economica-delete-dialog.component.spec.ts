/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAvaliacaoEconomicaDeleteDialogComponent } from 'app/entities/tipo-avaliacao-economica/tipo-avaliacao-economica-delete-dialog.component';
import { TipoAvaliacaoEconomicaService } from 'app/entities/tipo-avaliacao-economica/tipo-avaliacao-economica.service';

describe('Component Tests', () => {
    describe('TipoAvaliacaoEconomica Management Delete Component', () => {
        let comp: TipoAvaliacaoEconomicaDeleteDialogComponent;
        let fixture: ComponentFixture<TipoAvaliacaoEconomicaDeleteDialogComponent>;
        let service: TipoAvaliacaoEconomicaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAvaliacaoEconomicaDeleteDialogComponent]
            })
                .overrideTemplate(TipoAvaliacaoEconomicaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoAvaliacaoEconomicaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoAvaliacaoEconomicaService);
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
