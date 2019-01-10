/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoColaboradorDetailComponent } from 'app/entities/tipo-colaborador/tipo-colaborador-detail.component';
import { TipoColaborador } from 'app/shared/model/tipo-colaborador.model';

describe('Component Tests', () => {
    describe('TipoColaborador Management Detail Component', () => {
        let comp: TipoColaboradorDetailComponent;
        let fixture: ComponentFixture<TipoColaboradorDetailComponent>;
        const route = ({ data: of({ tipoColaborador: new TipoColaborador(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoColaboradorDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoColaboradorDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoColaboradorDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoColaborador).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
