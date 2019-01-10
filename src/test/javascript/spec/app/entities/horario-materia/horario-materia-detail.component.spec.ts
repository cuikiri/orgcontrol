/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { HorarioMateriaDetailComponent } from 'app/entities/horario-materia/horario-materia-detail.component';
import { HorarioMateria } from 'app/shared/model/horario-materia.model';

describe('Component Tests', () => {
    describe('HorarioMateria Management Detail Component', () => {
        let comp: HorarioMateriaDetailComponent;
        let fixture: ComponentFixture<HorarioMateriaDetailComponent>;
        const route = ({ data: of({ horarioMateria: new HorarioMateria(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [HorarioMateriaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(HorarioMateriaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HorarioMateriaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.horarioMateria).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
