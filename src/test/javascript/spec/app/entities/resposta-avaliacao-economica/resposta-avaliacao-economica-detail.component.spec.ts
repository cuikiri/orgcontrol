/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespostaAvaliacaoEconomicaDetailComponent } from 'app/entities/resposta-avaliacao-economica/resposta-avaliacao-economica-detail.component';
import { RespostaAvaliacaoEconomica } from 'app/shared/model/resposta-avaliacao-economica.model';

describe('Component Tests', () => {
    describe('RespostaAvaliacaoEconomica Management Detail Component', () => {
        let comp: RespostaAvaliacaoEconomicaDetailComponent;
        let fixture: ComponentFixture<RespostaAvaliacaoEconomicaDetailComponent>;
        const route = ({ data: of({ respostaAvaliacaoEconomica: new RespostaAvaliacaoEconomica(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespostaAvaliacaoEconomicaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RespostaAvaliacaoEconomicaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespostaAvaliacaoEconomicaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.respostaAvaliacaoEconomica).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
