/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoParteBlocoDeleteDialogComponent } from 'app/entities/tipo-parte-bloco/tipo-parte-bloco-delete-dialog.component';
import { TipoParteBlocoService } from 'app/entities/tipo-parte-bloco/tipo-parte-bloco.service';

describe('Component Tests', () => {
    describe('TipoParteBloco Management Delete Component', () => {
        let comp: TipoParteBlocoDeleteDialogComponent;
        let fixture: ComponentFixture<TipoParteBlocoDeleteDialogComponent>;
        let service: TipoParteBlocoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoParteBlocoDeleteDialogComponent]
            })
                .overrideTemplate(TipoParteBlocoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoParteBlocoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoParteBlocoService);
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
