/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { PlanejamentoEnsinoDeleteDialogComponent } from 'app/entities/planejamento-ensino/planejamento-ensino-delete-dialog.component';
import { PlanejamentoEnsinoService } from 'app/entities/planejamento-ensino/planejamento-ensino.service';

describe('Component Tests', () => {
    describe('PlanejamentoEnsino Management Delete Component', () => {
        let comp: PlanejamentoEnsinoDeleteDialogComponent;
        let fixture: ComponentFixture<PlanejamentoEnsinoDeleteDialogComponent>;
        let service: PlanejamentoEnsinoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PlanejamentoEnsinoDeleteDialogComponent]
            })
                .overrideTemplate(PlanejamentoEnsinoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanejamentoEnsinoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanejamentoEnsinoService);
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
