/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { DadoBiologicoDeleteDialogComponent } from 'app/entities/dado-biologico/dado-biologico-delete-dialog.component';
import { DadoBiologicoService } from 'app/entities/dado-biologico/dado-biologico.service';

describe('Component Tests', () => {
    describe('DadoBiologico Management Delete Component', () => {
        let comp: DadoBiologicoDeleteDialogComponent;
        let fixture: ComponentFixture<DadoBiologicoDeleteDialogComponent>;
        let service: DadoBiologicoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DadoBiologicoDeleteDialogComponent]
            })
                .overrideTemplate(DadoBiologicoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DadoBiologicoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DadoBiologicoService);
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
