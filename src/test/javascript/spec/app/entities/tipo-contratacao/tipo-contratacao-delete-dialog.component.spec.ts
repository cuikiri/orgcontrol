/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoContratacaoDeleteDialogComponent } from 'app/entities/tipo-contratacao/tipo-contratacao-delete-dialog.component';
import { TipoContratacaoService } from 'app/entities/tipo-contratacao/tipo-contratacao.service';

describe('Component Tests', () => {
    describe('TipoContratacao Management Delete Component', () => {
        let comp: TipoContratacaoDeleteDialogComponent;
        let fixture: ComponentFixture<TipoContratacaoDeleteDialogComponent>;
        let service: TipoContratacaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoContratacaoDeleteDialogComponent]
            })
                .overrideTemplate(TipoContratacaoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoContratacaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoContratacaoService);
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
