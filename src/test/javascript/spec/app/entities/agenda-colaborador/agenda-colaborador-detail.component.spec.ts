/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AgendaColaboradorDetailComponent } from 'app/entities/agenda-colaborador/agenda-colaborador-detail.component';
import { AgendaColaborador } from 'app/shared/model/agenda-colaborador.model';

describe('Component Tests', () => {
    describe('AgendaColaborador Management Detail Component', () => {
        let comp: AgendaColaboradorDetailComponent;
        let fixture: ComponentFixture<AgendaColaboradorDetailComponent>;
        const route = ({ data: of({ agendaColaborador: new AgendaColaborador(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AgendaColaboradorDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AgendaColaboradorDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AgendaColaboradorDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.agendaColaborador).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
