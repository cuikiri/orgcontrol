/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespostaAvaliacaoEconomicaDeleteDialogComponent } from 'app/entities/resposta-avaliacao-economica/resposta-avaliacao-economica-delete-dialog.component';
import { RespostaAvaliacaoEconomicaService } from 'app/entities/resposta-avaliacao-economica/resposta-avaliacao-economica.service';

describe('Component Tests', () => {
    describe('RespostaAvaliacaoEconomica Management Delete Component', () => {
        let comp: RespostaAvaliacaoEconomicaDeleteDialogComponent;
        let fixture: ComponentFixture<RespostaAvaliacaoEconomicaDeleteDialogComponent>;
        let service: RespostaAvaliacaoEconomicaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespostaAvaliacaoEconomicaDeleteDialogComponent]
            })
                .overrideTemplate(RespostaAvaliacaoEconomicaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespostaAvaliacaoEconomicaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespostaAvaliacaoEconomicaService);
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
