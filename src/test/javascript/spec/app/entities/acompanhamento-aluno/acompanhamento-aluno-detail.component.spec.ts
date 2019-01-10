/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AcompanhamentoAlunoDetailComponent } from 'app/entities/acompanhamento-aluno/acompanhamento-aluno-detail.component';
import { AcompanhamentoAluno } from 'app/shared/model/acompanhamento-aluno.model';

describe('Component Tests', () => {
    describe('AcompanhamentoAluno Management Detail Component', () => {
        let comp: AcompanhamentoAlunoDetailComponent;
        let fixture: ComponentFixture<AcompanhamentoAlunoDetailComponent>;
        const route = ({ data: of({ acompanhamentoAluno: new AcompanhamentoAluno(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AcompanhamentoAlunoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AcompanhamentoAlunoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AcompanhamentoAlunoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.acompanhamentoAluno).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
