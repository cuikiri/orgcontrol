/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ReligiaoDetailComponent } from 'app/entities/religiao/religiao-detail.component';
import { Religiao } from 'app/shared/model/religiao.model';

describe('Component Tests', () => {
    describe('Religiao Management Detail Component', () => {
        let comp: ReligiaoDetailComponent;
        let fixture: ComponentFixture<ReligiaoDetailComponent>;
        const route = ({ data: of({ religiao: new Religiao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ReligiaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ReligiaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReligiaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.religiao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
