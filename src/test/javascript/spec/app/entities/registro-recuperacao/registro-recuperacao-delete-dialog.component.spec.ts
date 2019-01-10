/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { RegistroRecuperacaoDeleteDialogComponent } from 'app/entities/registro-recuperacao/registro-recuperacao-delete-dialog.component';
import { RegistroRecuperacaoService } from 'app/entities/registro-recuperacao/registro-recuperacao.service';

describe('Component Tests', () => {
    describe('RegistroRecuperacao Management Delete Component', () => {
        let comp: RegistroRecuperacaoDeleteDialogComponent;
        let fixture: ComponentFixture<RegistroRecuperacaoDeleteDialogComponent>;
        let service: RegistroRecuperacaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RegistroRecuperacaoDeleteDialogComponent]
            })
                .overrideTemplate(RegistroRecuperacaoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RegistroRecuperacaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RegistroRecuperacaoService);
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
