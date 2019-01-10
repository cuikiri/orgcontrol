/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAvalOptativaEconomicaDetailComponent } from 'app/entities/resp-aval-optativa-economica/resp-aval-optativa-economica-detail.component';
import { RespAvalOptativaEconomica } from 'app/shared/model/resp-aval-optativa-economica.model';

describe('Component Tests', () => {
    describe('RespAvalOptativaEconomica Management Detail Component', () => {
        let comp: RespAvalOptativaEconomicaDetailComponent;
        let fixture: ComponentFixture<RespAvalOptativaEconomicaDetailComponent>;
        const route = ({ data: of({ respAvalOptativaEconomica: new RespAvalOptativaEconomica(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAvalOptativaEconomicaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RespAvalOptativaEconomicaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespAvalOptativaEconomicaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.respAvalOptativaEconomica).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
