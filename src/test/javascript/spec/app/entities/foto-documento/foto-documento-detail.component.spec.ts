/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { FotoDocumentoDetailComponent } from 'app/entities/foto-documento/foto-documento-detail.component';
import { FotoDocumento } from 'app/shared/model/foto-documento.model';

describe('Component Tests', () => {
    describe('FotoDocumento Management Detail Component', () => {
        let comp: FotoDocumentoDetailComponent;
        let fixture: ComponentFixture<FotoDocumentoDetailComponent>;
        const route = ({ data: of({ fotoDocumento: new FotoDocumento(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [FotoDocumentoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FotoDocumentoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FotoDocumentoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.fotoDocumento).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
