/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AcompanhamentoEscolarAlunoDetailComponent } from 'app/entities/acompanhamento-escolar-aluno/acompanhamento-escolar-aluno-detail.component';
import { AcompanhamentoEscolarAluno } from 'app/shared/model/acompanhamento-escolar-aluno.model';

describe('Component Tests', () => {
    describe('AcompanhamentoEscolarAluno Management Detail Component', () => {
        let comp: AcompanhamentoEscolarAlunoDetailComponent;
        let fixture: ComponentFixture<AcompanhamentoEscolarAlunoDetailComponent>;
        const route = ({ data: of({ acompanhamentoEscolarAluno: new AcompanhamentoEscolarAluno(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AcompanhamentoEscolarAlunoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AcompanhamentoEscolarAlunoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AcompanhamentoEscolarAlunoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.acompanhamentoEscolarAluno).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
