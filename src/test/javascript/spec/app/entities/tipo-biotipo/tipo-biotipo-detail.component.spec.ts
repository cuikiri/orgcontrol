/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoBiotipoDetailComponent } from 'app/entities/tipo-biotipo/tipo-biotipo-detail.component';
import { TipoBiotipo } from 'app/shared/model/tipo-biotipo.model';

describe('Component Tests', () => {
    describe('TipoBiotipo Management Detail Component', () => {
        let comp: TipoBiotipoDetailComponent;
        let fixture: ComponentFixture<TipoBiotipoDetailComponent>;
        const route = ({ data: of({ tipoBiotipo: new TipoBiotipo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoBiotipoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoBiotipoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoBiotipoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoBiotipo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
