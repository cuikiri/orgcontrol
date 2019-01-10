/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AcompanhamentoAtividadeDetailComponent } from 'app/entities/acompanhamento-atividade/acompanhamento-atividade-detail.component';
import { AcompanhamentoAtividade } from 'app/shared/model/acompanhamento-atividade.model';

describe('Component Tests', () => {
    describe('AcompanhamentoAtividade Management Detail Component', () => {
        let comp: AcompanhamentoAtividadeDetailComponent;
        let fixture: ComponentFixture<AcompanhamentoAtividadeDetailComponent>;
        const route = ({ data: of({ acompanhamentoAtividade: new AcompanhamentoAtividade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AcompanhamentoAtividadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AcompanhamentoAtividadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AcompanhamentoAtividadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.acompanhamentoAtividade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
