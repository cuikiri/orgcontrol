/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAcompanhamentoAlunoDeleteDialogComponent } from 'app/entities/tipo-acompanhamento-aluno/tipo-acompanhamento-aluno-delete-dialog.component';
import { TipoAcompanhamentoAlunoService } from 'app/entities/tipo-acompanhamento-aluno/tipo-acompanhamento-aluno.service';

describe('Component Tests', () => {
    describe('TipoAcompanhamentoAluno Management Delete Component', () => {
        let comp: TipoAcompanhamentoAlunoDeleteDialogComponent;
        let fixture: ComponentFixture<TipoAcompanhamentoAlunoDeleteDialogComponent>;
        let service: TipoAcompanhamentoAlunoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAcompanhamentoAlunoDeleteDialogComponent]
            })
                .overrideTemplate(TipoAcompanhamentoAlunoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoAcompanhamentoAlunoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoAcompanhamentoAlunoService);
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
