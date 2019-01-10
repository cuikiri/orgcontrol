/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAvaliacaoEconomicaDetailComponent } from 'app/entities/tipo-avaliacao-economica/tipo-avaliacao-economica-detail.component';
import { TipoAvaliacaoEconomica } from 'app/shared/model/tipo-avaliacao-economica.model';

describe('Component Tests', () => {
    describe('TipoAvaliacaoEconomica Management Detail Component', () => {
        let comp: TipoAvaliacaoEconomicaDetailComponent;
        let fixture: ComponentFixture<TipoAvaliacaoEconomicaDetailComponent>;
        const route = ({ data: of({ tipoAvaliacaoEconomica: new TipoAvaliacaoEconomica(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAvaliacaoEconomicaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoAvaliacaoEconomicaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoAvaliacaoEconomicaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoAvaliacaoEconomica).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
