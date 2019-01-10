/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PeriodoDuracaoDetailComponent } from 'app/entities/periodo-duracao/periodo-duracao-detail.component';
import { PeriodoDuracao } from 'app/shared/model/periodo-duracao.model';

describe('Component Tests', () => {
    describe('PeriodoDuracao Management Detail Component', () => {
        let comp: PeriodoDuracaoDetailComponent;
        let fixture: ComponentFixture<PeriodoDuracaoDetailComponent>;
        const route = ({ data: of({ periodoDuracao: new PeriodoDuracao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PeriodoDuracaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PeriodoDuracaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PeriodoDuracaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.periodoDuracao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
