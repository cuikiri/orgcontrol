/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { ObservacaoCoordenadorDeleteDialogComponent } from 'app/entities/observacao-coordenador/observacao-coordenador-delete-dialog.component';
import { ObservacaoCoordenadorService } from 'app/entities/observacao-coordenador/observacao-coordenador.service';

describe('Component Tests', () => {
    describe('ObservacaoCoordenador Management Delete Component', () => {
        let comp: ObservacaoCoordenadorDeleteDialogComponent;
        let fixture: ComponentFixture<ObservacaoCoordenadorDeleteDialogComponent>;
        let service: ObservacaoCoordenadorService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ObservacaoCoordenadorDeleteDialogComponent]
            })
                .overrideTemplate(ObservacaoCoordenadorDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ObservacaoCoordenadorDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ObservacaoCoordenadorService);
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
