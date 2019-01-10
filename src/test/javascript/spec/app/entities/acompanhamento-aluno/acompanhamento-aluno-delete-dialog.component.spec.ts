/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { AcompanhamentoAlunoDeleteDialogComponent } from 'app/entities/acompanhamento-aluno/acompanhamento-aluno-delete-dialog.component';
import { AcompanhamentoAlunoService } from 'app/entities/acompanhamento-aluno/acompanhamento-aluno.service';

describe('Component Tests', () => {
    describe('AcompanhamentoAluno Management Delete Component', () => {
        let comp: AcompanhamentoAlunoDeleteDialogComponent;
        let fixture: ComponentFixture<AcompanhamentoAlunoDeleteDialogComponent>;
        let service: AcompanhamentoAlunoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AcompanhamentoAlunoDeleteDialogComponent]
            })
                .overrideTemplate(AcompanhamentoAlunoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AcompanhamentoAlunoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AcompanhamentoAlunoService);
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
