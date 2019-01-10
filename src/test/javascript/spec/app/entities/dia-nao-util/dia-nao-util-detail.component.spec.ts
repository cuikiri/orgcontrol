/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DiaNaoUtilDetailComponent } from 'app/entities/dia-nao-util/dia-nao-util-detail.component';
import { DiaNaoUtil } from 'app/shared/model/dia-nao-util.model';

describe('Component Tests', () => {
    describe('DiaNaoUtil Management Detail Component', () => {
        let comp: DiaNaoUtilDetailComponent;
        let fixture: ComponentFixture<DiaNaoUtilDetailComponent>;
        const route = ({ data: of({ diaNaoUtil: new DiaNaoUtil(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DiaNaoUtilDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DiaNaoUtilDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DiaNaoUtilDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.diaNaoUtil).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
