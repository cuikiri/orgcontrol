/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoConceitoDetailComponent } from 'app/entities/tipo-conceito/tipo-conceito-detail.component';
import { TipoConceito } from 'app/shared/model/tipo-conceito.model';

describe('Component Tests', () => {
    describe('TipoConceito Management Detail Component', () => {
        let comp: TipoConceitoDetailComponent;
        let fixture: ComponentFixture<TipoConceitoDetailComponent>;
        const route = ({ data: of({ tipoConceito: new TipoConceito(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoConceitoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoConceitoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoConceitoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoConceito).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
