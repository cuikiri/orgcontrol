/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoCursoDeleteDialogComponent } from 'app/entities/tipo-curso/tipo-curso-delete-dialog.component';
import { TipoCursoService } from 'app/entities/tipo-curso/tipo-curso.service';

describe('Component Tests', () => {
    describe('TipoCurso Management Delete Component', () => {
        let comp: TipoCursoDeleteDialogComponent;
        let fixture: ComponentFixture<TipoCursoDeleteDialogComponent>;
        let service: TipoCursoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoCursoDeleteDialogComponent]
            })
                .overrideTemplate(TipoCursoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoCursoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoCursoService);
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
