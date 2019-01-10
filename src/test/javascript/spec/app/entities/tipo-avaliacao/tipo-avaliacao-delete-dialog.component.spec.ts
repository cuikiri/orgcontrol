/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAvaliacaoDeleteDialogComponent } from 'app/entities/tipo-avaliacao/tipo-avaliacao-delete-dialog.component';
import { TipoAvaliacaoService } from 'app/entities/tipo-avaliacao/tipo-avaliacao.service';

describe('Component Tests', () => {
    describe('TipoAvaliacao Management Delete Component', () => {
        let comp: TipoAvaliacaoDeleteDialogComponent;
        let fixture: ComponentFixture<TipoAvaliacaoDeleteDialogComponent>;
        let service: TipoAvaliacaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAvaliacaoDeleteDialogComponent]
            })
                .overrideTemplate(TipoAvaliacaoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoAvaliacaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoAvaliacaoService);
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
