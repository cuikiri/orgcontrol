/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAcompanhamentoAlunoUpdateComponent } from 'app/entities/tipo-acompanhamento-aluno/tipo-acompanhamento-aluno-update.component';
import { TipoAcompanhamentoAlunoService } from 'app/entities/tipo-acompanhamento-aluno/tipo-acompanhamento-aluno.service';
import { TipoAcompanhamentoAluno } from 'app/shared/model/tipo-acompanhamento-aluno.model';

describe('Component Tests', () => {
    describe('TipoAcompanhamentoAluno Management Update Component', () => {
        let comp: TipoAcompanhamentoAlunoUpdateComponent;
        let fixture: ComponentFixture<TipoAcompanhamentoAlunoUpdateComponent>;
        let service: TipoAcompanhamentoAlunoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAcompanhamentoAlunoUpdateComponent]
            })
                .overrideTemplate(TipoAcompanhamentoAlunoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoAcompanhamentoAlunoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoAcompanhamentoAlunoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoAcompanhamentoAluno(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoAcompanhamentoAluno = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoAcompanhamentoAluno();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoAcompanhamentoAluno = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
