/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoContratacaoDetailComponent } from 'app/entities/tipo-contratacao/tipo-contratacao-detail.component';
import { TipoContratacao } from 'app/shared/model/tipo-contratacao.model';

describe('Component Tests', () => {
    describe('TipoContratacao Management Detail Component', () => {
        let comp: TipoContratacaoDetailComponent;
        let fixture: ComponentFixture<TipoContratacaoDetailComponent>;
        const route = ({ data: of({ tipoContratacao: new TipoContratacao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoContratacaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoContratacaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoContratacaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoContratacao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
