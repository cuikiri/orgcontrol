/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { OpcaoRespAtividadeDetailComponent } from 'app/entities/opcao-resp-atividade/opcao-resp-atividade-detail.component';
import { OpcaoRespAtividade } from 'app/shared/model/opcao-resp-atividade.model';

describe('Component Tests', () => {
    describe('OpcaoRespAtividade Management Detail Component', () => {
        let comp: OpcaoRespAtividadeDetailComponent;
        let fixture: ComponentFixture<OpcaoRespAtividadeDetailComponent>;
        const route = ({ data: of({ opcaoRespAtividade: new OpcaoRespAtividade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [OpcaoRespAtividadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OpcaoRespAtividadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OpcaoRespAtividadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.opcaoRespAtividade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
