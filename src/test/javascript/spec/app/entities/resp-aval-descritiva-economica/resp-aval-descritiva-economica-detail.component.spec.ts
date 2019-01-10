/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAvalDescritivaEconomicaDetailComponent } from 'app/entities/resp-aval-descritiva-economica/resp-aval-descritiva-economica-detail.component';
import { RespAvalDescritivaEconomica } from 'app/shared/model/resp-aval-descritiva-economica.model';

describe('Component Tests', () => {
    describe('RespAvalDescritivaEconomica Management Detail Component', () => {
        let comp: RespAvalDescritivaEconomicaDetailComponent;
        let fixture: ComponentFixture<RespAvalDescritivaEconomicaDetailComponent>;
        const route = ({ data: of({ respAvalDescritivaEconomica: new RespAvalDescritivaEconomica(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAvalDescritivaEconomicaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RespAvalDescritivaEconomicaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespAvalDescritivaEconomicaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.respAvalDescritivaEconomica).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
