/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoUnidadeDeleteDialogComponent } from 'app/entities/tipo-unidade/tipo-unidade-delete-dialog.component';
import { TipoUnidadeService } from 'app/entities/tipo-unidade/tipo-unidade.service';

describe('Component Tests', () => {
    describe('TipoUnidade Management Delete Component', () => {
        let comp: TipoUnidadeDeleteDialogComponent;
        let fixture: ComponentFixture<TipoUnidadeDeleteDialogComponent>;
        let service: TipoUnidadeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoUnidadeDeleteDialogComponent]
            })
                .overrideTemplate(TipoUnidadeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoUnidadeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoUnidadeService);
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
