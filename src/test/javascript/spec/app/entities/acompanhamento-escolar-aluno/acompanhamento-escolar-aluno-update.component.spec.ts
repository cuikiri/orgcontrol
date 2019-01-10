/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AcompanhamentoEscolarAlunoUpdateComponent } from 'app/entities/acompanhamento-escolar-aluno/acompanhamento-escolar-aluno-update.component';
import { AcompanhamentoEscolarAlunoService } from 'app/entities/acompanhamento-escolar-aluno/acompanhamento-escolar-aluno.service';
import { AcompanhamentoEscolarAluno } from 'app/shared/model/acompanhamento-escolar-aluno.model';

describe('Component Tests', () => {
    describe('AcompanhamentoEscolarAluno Management Update Component', () => {
        let comp: AcompanhamentoEscolarAlunoUpdateComponent;
        let fixture: ComponentFixture<AcompanhamentoEscolarAlunoUpdateComponent>;
        let service: AcompanhamentoEscolarAlunoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AcompanhamentoEscolarAlunoUpdateComponent]
            })
                .overrideTemplate(AcompanhamentoEscolarAlunoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AcompanhamentoEscolarAlunoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AcompanhamentoEscolarAlunoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AcompanhamentoEscolarAluno(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.acompanhamentoEscolarAluno = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AcompanhamentoEscolarAluno();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.acompanhamentoEscolarAluno = entity;
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
