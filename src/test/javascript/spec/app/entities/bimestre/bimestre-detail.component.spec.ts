/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { BimestreDetailComponent } from 'app/entities/bimestre/bimestre-detail.component';
import { Bimestre } from 'app/shared/model/bimestre.model';

describe('Component Tests', () => {
    describe('Bimestre Management Detail Component', () => {
        let comp: BimestreDetailComponent;
        let fixture: ComponentFixture<BimestreDetailComponent>;
        const route = ({ data: of({ bimestre: new Bimestre(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [BimestreDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BimestreDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BimestreDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.bimestre).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
