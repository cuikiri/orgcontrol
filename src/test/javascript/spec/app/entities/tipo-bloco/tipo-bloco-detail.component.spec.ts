/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoBlocoDetailComponent } from 'app/entities/tipo-bloco/tipo-bloco-detail.component';
import { TipoBloco } from 'app/shared/model/tipo-bloco.model';

describe('Component Tests', () => {
    describe('TipoBloco Management Detail Component', () => {
        let comp: TipoBlocoDetailComponent;
        let fixture: ComponentFixture<TipoBlocoDetailComponent>;
        const route = ({ data: of({ tipoBloco: new TipoBloco(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoBlocoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoBlocoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoBlocoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoBloco).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
