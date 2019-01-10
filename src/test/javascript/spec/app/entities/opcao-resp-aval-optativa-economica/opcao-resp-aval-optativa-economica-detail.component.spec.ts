/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { OpcaoRespAvalOptativaEconomicaDetailComponent } from 'app/entities/opcao-resp-aval-optativa-economica/opcao-resp-aval-optativa-economica-detail.component';
import { OpcaoRespAvalOptativaEconomica } from 'app/shared/model/opcao-resp-aval-optativa-economica.model';

describe('Component Tests', () => {
    describe('OpcaoRespAvalOptativaEconomica Management Detail Component', () => {
        let comp: OpcaoRespAvalOptativaEconomicaDetailComponent;
        let fixture: ComponentFixture<OpcaoRespAvalOptativaEconomicaDetailComponent>;
        const route = ({ data: of({ opcaoRespAvalOptativaEconomica: new OpcaoRespAvalOptativaEconomica(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [OpcaoRespAvalOptativaEconomicaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OpcaoRespAvalOptativaEconomicaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OpcaoRespAvalOptativaEconomicaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.opcaoRespAvalOptativaEconomica).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
