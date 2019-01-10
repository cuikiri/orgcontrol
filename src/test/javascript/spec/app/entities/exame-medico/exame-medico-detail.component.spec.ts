/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ExameMedicoDetailComponent } from 'app/entities/exame-medico/exame-medico-detail.component';
import { ExameMedico } from 'app/shared/model/exame-medico.model';

describe('Component Tests', () => {
    describe('ExameMedico Management Detail Component', () => {
        let comp: ExameMedicoDetailComponent;
        let fixture: ComponentFixture<ExameMedicoDetailComponent>;
        const route = ({ data: of({ exameMedico: new ExameMedico(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ExameMedicoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ExameMedicoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExameMedicoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.exameMedico).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
