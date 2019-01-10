/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAcompanhamentoAtividadeDetailComponent } from 'app/entities/tipo-acompanhamento-atividade/tipo-acompanhamento-atividade-detail.component';
import { TipoAcompanhamentoAtividade } from 'app/shared/model/tipo-acompanhamento-atividade.model';

describe('Component Tests', () => {
    describe('TipoAcompanhamentoAtividade Management Detail Component', () => {
        let comp: TipoAcompanhamentoAtividadeDetailComponent;
        let fixture: ComponentFixture<TipoAcompanhamentoAtividadeDetailComponent>;
        const route = ({ data: of({ tipoAcompanhamentoAtividade: new TipoAcompanhamentoAtividade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAcompanhamentoAtividadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoAcompanhamentoAtividadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoAcompanhamentoAtividadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoAcompanhamentoAtividade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
