/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoUnidadeDetailComponent } from 'app/entities/tipo-unidade/tipo-unidade-detail.component';
import { TipoUnidade } from 'app/shared/model/tipo-unidade.model';

describe('Component Tests', () => {
    describe('TipoUnidade Management Detail Component', () => {
        let comp: TipoUnidadeDetailComponent;
        let fixture: ComponentFixture<TipoUnidadeDetailComponent>;
        const route = ({ data: of({ tipoUnidade: new TipoUnidade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoUnidadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoUnidadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoUnidadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoUnidade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
