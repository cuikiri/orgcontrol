/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DiaSemanaDetailComponent } from 'app/entities/dia-semana/dia-semana-detail.component';
import { DiaSemana } from 'app/shared/model/dia-semana.model';

describe('Component Tests', () => {
    describe('DiaSemana Management Detail Component', () => {
        let comp: DiaSemanaDetailComponent;
        let fixture: ComponentFixture<DiaSemanaDetailComponent>;
        const route = ({ data: of({ diaSemana: new DiaSemana(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DiaSemanaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DiaSemanaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DiaSemanaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.diaSemana).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
