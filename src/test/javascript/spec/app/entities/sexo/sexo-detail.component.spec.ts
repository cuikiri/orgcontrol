/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { SexoDetailComponent } from 'app/entities/sexo/sexo-detail.component';
import { Sexo } from 'app/shared/model/sexo.model';

describe('Component Tests', () => {
    describe('Sexo Management Detail Component', () => {
        let comp: SexoDetailComponent;
        let fixture: ComponentFixture<SexoDetailComponent>;
        const route = ({ data: of({ sexo: new Sexo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [SexoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SexoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SexoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sexo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
