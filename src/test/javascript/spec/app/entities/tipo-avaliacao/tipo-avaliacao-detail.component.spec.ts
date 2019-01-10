/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAvaliacaoDetailComponent } from 'app/entities/tipo-avaliacao/tipo-avaliacao-detail.component';
import { TipoAvaliacao } from 'app/shared/model/tipo-avaliacao.model';

describe('Component Tests', () => {
    describe('TipoAvaliacao Management Detail Component', () => {
        let comp: TipoAvaliacaoDetailComponent;
        let fixture: ComponentFixture<TipoAvaliacaoDetailComponent>;
        const route = ({ data: of({ tipoAvaliacao: new TipoAvaliacao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAvaliacaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoAvaliacaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoAvaliacaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoAvaliacao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
