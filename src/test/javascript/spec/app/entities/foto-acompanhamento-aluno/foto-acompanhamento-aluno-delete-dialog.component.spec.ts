/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { FotoAcompanhamentoAlunoDeleteDialogComponent } from 'app/entities/foto-acompanhamento-aluno/foto-acompanhamento-aluno-delete-dialog.component';
import { FotoAcompanhamentoAlunoService } from 'app/entities/foto-acompanhamento-aluno/foto-acompanhamento-aluno.service';

describe('Component Tests', () => {
    describe('FotoAcompanhamentoAluno Management Delete Component', () => {
        let comp: FotoAcompanhamentoAlunoDeleteDialogComponent;
        let fixture: ComponentFixture<FotoAcompanhamentoAlunoDeleteDialogComponent>;
        let service: FotoAcompanhamentoAlunoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [FotoAcompanhamentoAlunoDeleteDialogComponent]
            })
                .overrideTemplate(FotoAcompanhamentoAlunoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FotoAcompanhamentoAlunoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FotoAcompanhamentoAlunoService);
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
