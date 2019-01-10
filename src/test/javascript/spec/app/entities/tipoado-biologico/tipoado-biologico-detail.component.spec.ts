/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoadoBiologicoDetailComponent } from 'app/entities/tipoado-biologico/tipoado-biologico-detail.component';
import { TipoadoBiologico } from 'app/shared/model/tipoado-biologico.model';

describe('Component Tests', () => {
    describe('TipoadoBiologico Management Detail Component', () => {
        let comp: TipoadoBiologicoDetailComponent;
        let fixture: ComponentFixture<TipoadoBiologicoDetailComponent>;
        const route = ({ data: of({ tipoadoBiologico: new TipoadoBiologico(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoadoBiologicoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoadoBiologicoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoadoBiologicoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoadoBiologico).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
