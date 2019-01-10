/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DependenteLegalDetailComponent } from 'app/entities/dependente-legal/dependente-legal-detail.component';
import { DependenteLegal } from 'app/shared/model/dependente-legal.model';

describe('Component Tests', () => {
    describe('DependenteLegal Management Detail Component', () => {
        let comp: DependenteLegalDetailComponent;
        let fixture: ComponentFixture<DependenteLegalDetailComponent>;
        const route = ({ data: of({ dependenteLegal: new DependenteLegal(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DependenteLegalDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DependenteLegalDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DependenteLegalDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.dependenteLegal).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
