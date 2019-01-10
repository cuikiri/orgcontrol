/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAtividadeDeleteDialogComponent } from 'app/entities/tipo-atividade/tipo-atividade-delete-dialog.component';
import { TipoAtividadeService } from 'app/entities/tipo-atividade/tipo-atividade.service';

describe('Component Tests', () => {
    describe('TipoAtividade Management Delete Component', () => {
        let comp: TipoAtividadeDeleteDialogComponent;
        let fixture: ComponentFixture<TipoAtividadeDeleteDialogComponent>;
        let service: TipoAtividadeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAtividadeDeleteDialogComponent]
            })
                .overrideTemplate(TipoAtividadeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoAtividadeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoAtividadeService);
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
