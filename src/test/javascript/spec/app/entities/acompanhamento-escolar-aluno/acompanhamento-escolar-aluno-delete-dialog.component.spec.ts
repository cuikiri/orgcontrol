/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { AcompanhamentoEscolarAlunoDeleteDialogComponent } from 'app/entities/acompanhamento-escolar-aluno/acompanhamento-escolar-aluno-delete-dialog.component';
import { AcompanhamentoEscolarAlunoService } from 'app/entities/acompanhamento-escolar-aluno/acompanhamento-escolar-aluno.service';

describe('Component Tests', () => {
    describe('AcompanhamentoEscolarAluno Management Delete Component', () => {
        let comp: AcompanhamentoEscolarAlunoDeleteDialogComponent;
        let fixture: ComponentFixture<AcompanhamentoEscolarAlunoDeleteDialogComponent>;
        let service: AcompanhamentoEscolarAlunoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AcompanhamentoEscolarAlunoDeleteDialogComponent]
            })
                .overrideTemplate(AcompanhamentoEscolarAlunoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AcompanhamentoEscolarAlunoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AcompanhamentoEscolarAlunoService);
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
