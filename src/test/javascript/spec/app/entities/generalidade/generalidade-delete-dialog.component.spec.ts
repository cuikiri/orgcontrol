/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { GeneralidadeDeleteDialogComponent } from 'app/entities/generalidade/generalidade-delete-dialog.component';
import { GeneralidadeService } from 'app/entities/generalidade/generalidade.service';

describe('Component Tests', () => {
    describe('Generalidade Management Delete Component', () => {
        let comp: GeneralidadeDeleteDialogComponent;
        let fixture: ComponentFixture<GeneralidadeDeleteDialogComponent>;
        let service: GeneralidadeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [GeneralidadeDeleteDialogComponent]
            })
                .overrideTemplate(GeneralidadeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GeneralidadeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GeneralidadeService);
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
