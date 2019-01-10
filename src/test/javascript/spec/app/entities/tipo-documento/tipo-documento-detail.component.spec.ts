/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoDocumentoDetailComponent } from 'app/entities/tipo-documento/tipo-documento-detail.component';
import { TipoDocumento } from 'app/shared/model/tipo-documento.model';

describe('Component Tests', () => {
    describe('TipoDocumento Management Detail Component', () => {
        let comp: TipoDocumentoDetailComponent;
        let fixture: ComponentFixture<TipoDocumentoDetailComponent>;
        const route = ({ data: of({ tipoDocumento: new TipoDocumento(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoDocumentoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoDocumentoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoDocumentoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoDocumento).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
