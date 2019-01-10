/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAvalDescritivaDeleteDialogComponent } from 'app/entities/resp-aval-descritiva/resp-aval-descritiva-delete-dialog.component';
import { RespAvalDescritivaService } from 'app/entities/resp-aval-descritiva/resp-aval-descritiva.service';

describe('Component Tests', () => {
    describe('RespAvalDescritiva Management Delete Component', () => {
        let comp: RespAvalDescritivaDeleteDialogComponent;
        let fixture: ComponentFixture<RespAvalDescritivaDeleteDialogComponent>;
        let service: RespAvalDescritivaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAvalDescritivaDeleteDialogComponent]
            })
                .overrideTemplate(RespAvalDescritivaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespAvalDescritivaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespAvalDescritivaService);
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
