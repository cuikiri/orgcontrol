/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { MotivoDiaNaoUtilDetailComponent } from 'app/entities/motivo-dia-nao-util/motivo-dia-nao-util-detail.component';
import { MotivoDiaNaoUtil } from 'app/shared/model/motivo-dia-nao-util.model';

describe('Component Tests', () => {
    describe('MotivoDiaNaoUtil Management Detail Component', () => {
        let comp: MotivoDiaNaoUtilDetailComponent;
        let fixture: ComponentFixture<MotivoDiaNaoUtilDetailComponent>;
        const route = ({ data: of({ motivoDiaNaoUtil: new MotivoDiaNaoUtil(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [MotivoDiaNaoUtilDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MotivoDiaNaoUtilDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MotivoDiaNaoUtilDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.motivoDiaNaoUtil).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
