/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { ParteBlocoDeleteDialogComponent } from 'app/entities/parte-bloco/parte-bloco-delete-dialog.component';
import { ParteBlocoService } from 'app/entities/parte-bloco/parte-bloco.service';

describe('Component Tests', () => {
    describe('ParteBloco Management Delete Component', () => {
        let comp: ParteBlocoDeleteDialogComponent;
        let fixture: ComponentFixture<ParteBlocoDeleteDialogComponent>;
        let service: ParteBlocoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ParteBlocoDeleteDialogComponent]
            })
                .overrideTemplate(ParteBlocoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParteBlocoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParteBlocoService);
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
