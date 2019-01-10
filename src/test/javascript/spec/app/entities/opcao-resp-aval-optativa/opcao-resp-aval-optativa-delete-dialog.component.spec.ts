/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { OpcaoRespAvalOptativaDeleteDialogComponent } from 'app/entities/opcao-resp-aval-optativa/opcao-resp-aval-optativa-delete-dialog.component';
import { OpcaoRespAvalOptativaService } from 'app/entities/opcao-resp-aval-optativa/opcao-resp-aval-optativa.service';

describe('Component Tests', () => {
    describe('OpcaoRespAvalOptativa Management Delete Component', () => {
        let comp: OpcaoRespAvalOptativaDeleteDialogComponent;
        let fixture: ComponentFixture<OpcaoRespAvalOptativaDeleteDialogComponent>;
        let service: OpcaoRespAvalOptativaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [OpcaoRespAvalOptativaDeleteDialogComponent]
            })
                .overrideTemplate(OpcaoRespAvalOptativaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OpcaoRespAvalOptativaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OpcaoRespAvalOptativaService);
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
