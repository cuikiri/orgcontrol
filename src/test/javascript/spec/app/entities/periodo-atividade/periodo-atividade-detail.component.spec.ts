/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PeriodoAtividadeDetailComponent } from 'app/entities/periodo-atividade/periodo-atividade-detail.component';
import { PeriodoAtividade } from 'app/shared/model/periodo-atividade.model';

describe('Component Tests', () => {
    describe('PeriodoAtividade Management Detail Component', () => {
        let comp: PeriodoAtividadeDetailComponent;
        let fixture: ComponentFixture<PeriodoAtividadeDetailComponent>;
        const route = ({ data: of({ periodoAtividade: new PeriodoAtividade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PeriodoAtividadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PeriodoAtividadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PeriodoAtividadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.periodoAtividade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
