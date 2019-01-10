/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AvaliacaoEconomicaDetailComponent } from 'app/entities/avaliacao-economica/avaliacao-economica-detail.component';
import { AvaliacaoEconomica } from 'app/shared/model/avaliacao-economica.model';

describe('Component Tests', () => {
    describe('AvaliacaoEconomica Management Detail Component', () => {
        let comp: AvaliacaoEconomicaDetailComponent;
        let fixture: ComponentFixture<AvaliacaoEconomicaDetailComponent>;
        const route = ({ data: of({ avaliacaoEconomica: new AvaliacaoEconomica(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AvaliacaoEconomicaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AvaliacaoEconomicaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AvaliacaoEconomicaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.avaliacaoEconomica).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
