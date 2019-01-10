/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PlanejamentoInstituicaoDetailComponent } from 'app/entities/planejamento-instituicao/planejamento-instituicao-detail.component';
import { PlanejamentoInstituicao } from 'app/shared/model/planejamento-instituicao.model';

describe('Component Tests', () => {
    describe('PlanejamentoInstituicao Management Detail Component', () => {
        let comp: PlanejamentoInstituicaoDetailComponent;
        let fixture: ComponentFixture<PlanejamentoInstituicaoDetailComponent>;
        const route = ({ data: of({ planejamentoInstituicao: new PlanejamentoInstituicao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PlanejamentoInstituicaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanejamentoInstituicaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanejamentoInstituicaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planejamentoInstituicao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
