/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAcompanhamentoAlunoDetailComponent } from 'app/entities/tipo-acompanhamento-aluno/tipo-acompanhamento-aluno-detail.component';
import { TipoAcompanhamentoAluno } from 'app/shared/model/tipo-acompanhamento-aluno.model';

describe('Component Tests', () => {
    describe('TipoAcompanhamentoAluno Management Detail Component', () => {
        let comp: TipoAcompanhamentoAlunoDetailComponent;
        let fixture: ComponentFixture<TipoAcompanhamentoAlunoDetailComponent>;
        const route = ({ data: of({ tipoAcompanhamentoAluno: new TipoAcompanhamentoAluno(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAcompanhamentoAlunoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoAcompanhamentoAlunoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoAcompanhamentoAlunoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoAcompanhamentoAluno).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
