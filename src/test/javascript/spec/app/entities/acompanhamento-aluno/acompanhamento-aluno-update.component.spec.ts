/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AcompanhamentoAlunoUpdateComponent } from 'app/entities/acompanhamento-aluno/acompanhamento-aluno-update.component';
import { AcompanhamentoAlunoService } from 'app/entities/acompanhamento-aluno/acompanhamento-aluno.service';
import { AcompanhamentoAluno } from 'app/shared/model/acompanhamento-aluno.model';

describe('Component Tests', () => {
    describe('AcompanhamentoAluno Management Update Component', () => {
        let comp: AcompanhamentoAlunoUpdateComponent;
        let fixture: ComponentFixture<AcompanhamentoAlunoUpdateComponent>;
        let service: AcompanhamentoAlunoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AcompanhamentoAlunoUpdateComponent]
            })
                .overrideTemplate(AcompanhamentoAlunoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AcompanhamentoAlunoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AcompanhamentoAlunoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AcompanhamentoAluno(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.acompanhamentoAluno = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AcompanhamentoAluno();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.acompanhamentoAluno = entity;
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
