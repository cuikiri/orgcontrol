/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { FotoAcompanhamentoAlunoDetailComponent } from 'app/entities/foto-acompanhamento-aluno/foto-acompanhamento-aluno-detail.component';
import { FotoAcompanhamentoAluno } from 'app/shared/model/foto-acompanhamento-aluno.model';

describe('Component Tests', () => {
    describe('FotoAcompanhamentoAluno Management Detail Component', () => {
        let comp: FotoAcompanhamentoAlunoDetailComponent;
        let fixture: ComponentFixture<FotoAcompanhamentoAlunoDetailComponent>;
        const route = ({ data: of({ fotoAcompanhamentoAluno: new FotoAcompanhamentoAluno(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [FotoAcompanhamentoAlunoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FotoAcompanhamentoAlunoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FotoAcompanhamentoAlunoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.fotoAcompanhamentoAluno).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
