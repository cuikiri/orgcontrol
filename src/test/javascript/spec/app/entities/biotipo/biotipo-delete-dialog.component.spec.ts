/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { BiotipoDeleteDialogComponent } from 'app/entities/biotipo/biotipo-delete-dialog.component';
import { BiotipoService } from 'app/entities/biotipo/biotipo.service';

describe('Component Tests', () => {
    describe('Biotipo Management Delete Component', () => {
        let comp: BiotipoDeleteDialogComponent;
        let fixture: ComponentFixture<BiotipoDeleteDialogComponent>;
        let service: BiotipoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [BiotipoDeleteDialogComponent]
            })
                .overrideTemplate(BiotipoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BiotipoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BiotipoService);
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
