/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { MateriaDetailComponent } from 'app/entities/materia/materia-detail.component';
import { Materia } from 'app/shared/model/materia.model';

describe('Component Tests', () => {
    describe('Materia Management Detail Component', () => {
        let comp: MateriaDetailComponent;
        let fixture: ComponentFixture<MateriaDetailComponent>;
        const route = ({ data: of({ materia: new Materia(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [MateriaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MateriaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MateriaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.materia).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
