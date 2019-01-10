/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespostaAvaliacaoDetailComponent } from 'app/entities/resposta-avaliacao/resposta-avaliacao-detail.component';
import { RespostaAvaliacao } from 'app/shared/model/resposta-avaliacao.model';

describe('Component Tests', () => {
    describe('RespostaAvaliacao Management Detail Component', () => {
        let comp: RespostaAvaliacaoDetailComponent;
        let fixture: ComponentFixture<RespostaAvaliacaoDetailComponent>;
        const route = ({ data: of({ respostaAvaliacao: new RespostaAvaliacao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespostaAvaliacaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RespostaAvaliacaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespostaAvaliacaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.respostaAvaliacao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
