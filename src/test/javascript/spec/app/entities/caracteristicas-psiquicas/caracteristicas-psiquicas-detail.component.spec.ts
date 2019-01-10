/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { CaracteristicasPsiquicasDetailComponent } from 'app/entities/caracteristicas-psiquicas/caracteristicas-psiquicas-detail.component';
import { CaracteristicasPsiquicas } from 'app/shared/model/caracteristicas-psiquicas.model';

describe('Component Tests', () => {
    describe('CaracteristicasPsiquicas Management Detail Component', () => {
        let comp: CaracteristicasPsiquicasDetailComponent;
        let fixture: ComponentFixture<CaracteristicasPsiquicasDetailComponent>;
        const route = ({ data: of({ caracteristicasPsiquicas: new CaracteristicasPsiquicas(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [CaracteristicasPsiquicasDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CaracteristicasPsiquicasDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CaracteristicasPsiquicasDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.caracteristicasPsiquicas).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
