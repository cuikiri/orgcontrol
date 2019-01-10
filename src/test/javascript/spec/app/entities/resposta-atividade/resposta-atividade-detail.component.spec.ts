/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespostaAtividadeDetailComponent } from 'app/entities/resposta-atividade/resposta-atividade-detail.component';
import { RespostaAtividade } from 'app/shared/model/resposta-atividade.model';

describe('Component Tests', () => {
    describe('RespostaAtividade Management Detail Component', () => {
        let comp: RespostaAtividadeDetailComponent;
        let fixture: ComponentFixture<RespostaAtividadeDetailComponent>;
        const route = ({ data: of({ respostaAtividade: new RespostaAtividade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespostaAtividadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RespostaAtividadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespostaAtividadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.respostaAtividade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
