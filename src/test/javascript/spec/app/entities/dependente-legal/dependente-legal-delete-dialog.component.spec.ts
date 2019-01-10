/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { DependenteLegalDeleteDialogComponent } from 'app/entities/dependente-legal/dependente-legal-delete-dialog.component';
import { DependenteLegalService } from 'app/entities/dependente-legal/dependente-legal.service';

describe('Component Tests', () => {
    describe('DependenteLegal Management Delete Component', () => {
        let comp: DependenteLegalDeleteDialogComponent;
        let fixture: ComponentFixture<DependenteLegalDeleteDialogComponent>;
        let service: DependenteLegalService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DependenteLegalDeleteDialogComponent]
            })
                .overrideTemplate(DependenteLegalDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DependenteLegalDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DependenteLegalService);
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
