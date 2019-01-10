/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { FotoAcompanhamentoAlunoUpdateComponent } from 'app/entities/foto-acompanhamento-aluno/foto-acompanhamento-aluno-update.component';
import { FotoAcompanhamentoAlunoService } from 'app/entities/foto-acompanhamento-aluno/foto-acompanhamento-aluno.service';
import { FotoAcompanhamentoAluno } from 'app/shared/model/foto-acompanhamento-aluno.model';

describe('Component Tests', () => {
    describe('FotoAcompanhamentoAluno Management Update Component', () => {
        let comp: FotoAcompanhamentoAlunoUpdateComponent;
        let fixture: ComponentFixture<FotoAcompanhamentoAlunoUpdateComponent>;
        let service: FotoAcompanhamentoAlunoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [FotoAcompanhamentoAlunoUpdateComponent]
            })
                .overrideTemplate(FotoAcompanhamentoAlunoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FotoAcompanhamentoAlunoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FotoAcompanhamentoAlunoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new FotoAcompanhamentoAluno(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.fotoAcompanhamentoAluno = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new FotoAcompanhamentoAluno();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.fotoAcompanhamentoAluno = entity;
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
