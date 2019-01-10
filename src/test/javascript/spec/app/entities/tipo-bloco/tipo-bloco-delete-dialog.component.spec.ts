/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoBlocoDeleteDialogComponent } from 'app/entities/tipo-bloco/tipo-bloco-delete-dialog.component';
import { TipoBlocoService } from 'app/entities/tipo-bloco/tipo-bloco.service';

describe('Component Tests', () => {
    describe('TipoBloco Management Delete Component', () => {
        let comp: TipoBlocoDeleteDialogComponent;
        let fixture: ComponentFixture<TipoBlocoDeleteDialogComponent>;
        let service: TipoBlocoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoBlocoDeleteDialogComponent]
            })
                .overrideTemplate(TipoBlocoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoBlocoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoBlocoService);
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
