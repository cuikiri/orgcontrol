/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PlanejamentoAtividadeDetailComponent } from 'app/entities/planejamento-atividade/planejamento-atividade-detail.component';
import { PlanejamentoAtividade } from 'app/shared/model/planejamento-atividade.model';

describe('Component Tests', () => {
    describe('PlanejamentoAtividade Management Detail Component', () => {
        let comp: PlanejamentoAtividadeDetailComponent;
        let fixture: ComponentFixture<PlanejamentoAtividadeDetailComponent>;
        const route = ({ data: of({ planejamentoAtividade: new PlanejamentoAtividade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PlanejamentoAtividadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanejamentoAtividadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanejamentoAtividadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planejamentoAtividade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
