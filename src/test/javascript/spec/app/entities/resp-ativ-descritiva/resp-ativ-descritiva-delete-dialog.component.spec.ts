/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAtivDescritivaDeleteDialogComponent } from 'app/entities/resp-ativ-descritiva/resp-ativ-descritiva-delete-dialog.component';
import { RespAtivDescritivaService } from 'app/entities/resp-ativ-descritiva/resp-ativ-descritiva.service';

describe('Component Tests', () => {
    describe('RespAtivDescritiva Management Delete Component', () => {
        let comp: RespAtivDescritivaDeleteDialogComponent;
        let fixture: ComponentFixture<RespAtivDescritivaDeleteDialogComponent>;
        let service: RespAtivDescritivaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAtivDescritivaDeleteDialogComponent]
            })
                .overrideTemplate(RespAtivDescritivaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespAtivDescritivaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespAtivDescritivaService);
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
