/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PlanejamentoEnsinoDetailComponent } from 'app/entities/planejamento-ensino/planejamento-ensino-detail.component';
import { PlanejamentoEnsino } from 'app/shared/model/planejamento-ensino.model';

describe('Component Tests', () => {
    describe('PlanejamentoEnsino Management Detail Component', () => {
        let comp: PlanejamentoEnsinoDetailComponent;
        let fixture: ComponentFixture<PlanejamentoEnsinoDetailComponent>;
        const route = ({ data: of({ planejamentoEnsino: new PlanejamentoEnsino(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PlanejamentoEnsinoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanejamentoEnsinoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanejamentoEnsinoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planejamentoEnsino).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
