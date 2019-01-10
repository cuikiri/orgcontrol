/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PeriodoSemanaDetailComponent } from 'app/entities/periodo-semana/periodo-semana-detail.component';
import { PeriodoSemana } from 'app/shared/model/periodo-semana.model';

describe('Component Tests', () => {
    describe('PeriodoSemana Management Detail Component', () => {
        let comp: PeriodoSemanaDetailComponent;
        let fixture: ComponentFixture<PeriodoSemanaDetailComponent>;
        const route = ({ data: of({ periodoSemana: new PeriodoSemana(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PeriodoSemanaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PeriodoSemanaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PeriodoSemanaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.periodoSemana).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
