/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ConceitoAcompanhamentoDetailComponent } from 'app/entities/conceito-acompanhamento/conceito-acompanhamento-detail.component';
import { ConceitoAcompanhamento } from 'app/shared/model/conceito-acompanhamento.model';

describe('Component Tests', () => {
    describe('ConceitoAcompanhamento Management Detail Component', () => {
        let comp: ConceitoAcompanhamentoDetailComponent;
        let fixture: ComponentFixture<ConceitoAcompanhamentoDetailComponent>;
        const route = ({ data: of({ conceitoAcompanhamento: new ConceitoAcompanhamento(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ConceitoAcompanhamentoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ConceitoAcompanhamentoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConceitoAcompanhamentoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.conceitoAcompanhamento).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
