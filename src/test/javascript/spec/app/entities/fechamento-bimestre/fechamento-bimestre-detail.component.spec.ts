/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { FechamentoBimestreDetailComponent } from 'app/entities/fechamento-bimestre/fechamento-bimestre-detail.component';
import { FechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';

describe('Component Tests', () => {
    describe('FechamentoBimestre Management Detail Component', () => {
        let comp: FechamentoBimestreDetailComponent;
        let fixture: ComponentFixture<FechamentoBimestreDetailComponent>;
        const route = ({ data: of({ fechamentoBimestre: new FechamentoBimestre(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [FechamentoBimestreDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FechamentoBimestreDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FechamentoBimestreDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.fechamentoBimestre).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
