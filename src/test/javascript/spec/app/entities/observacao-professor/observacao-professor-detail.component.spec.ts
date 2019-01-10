/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ObservacaoProfessorDetailComponent } from 'app/entities/observacao-professor/observacao-professor-detail.component';
import { ObservacaoProfessor } from 'app/shared/model/observacao-professor.model';

describe('Component Tests', () => {
    describe('ObservacaoProfessor Management Detail Component', () => {
        let comp: ObservacaoProfessorDetailComponent;
        let fixture: ComponentFixture<ObservacaoProfessorDetailComponent>;
        const route = ({ data: of({ observacaoProfessor: new ObservacaoProfessor(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ObservacaoProfessorDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ObservacaoProfessorDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ObservacaoProfessorDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.observacaoProfessor).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
