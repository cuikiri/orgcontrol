/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { EnsinoDetailComponent } from 'app/entities/ensino/ensino-detail.component';
import { Ensino } from 'app/shared/model/ensino.model';

describe('Component Tests', () => {
    describe('Ensino Management Detail Component', () => {
        let comp: EnsinoDetailComponent;
        let fixture: ComponentFixture<EnsinoDetailComponent>;
        const route = ({ data: of({ ensino: new Ensino(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [EnsinoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnsinoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnsinoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.ensino).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
