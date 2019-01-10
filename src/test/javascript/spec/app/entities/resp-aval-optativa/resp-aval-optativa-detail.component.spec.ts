/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAvalOptativaDetailComponent } from 'app/entities/resp-aval-optativa/resp-aval-optativa-detail.component';
import { RespAvalOptativa } from 'app/shared/model/resp-aval-optativa.model';

describe('Component Tests', () => {
    describe('RespAvalOptativa Management Detail Component', () => {
        let comp: RespAvalOptativaDetailComponent;
        let fixture: ComponentFixture<RespAvalOptativaDetailComponent>;
        const route = ({ data: of({ respAvalOptativa: new RespAvalOptativa(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAvalOptativaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RespAvalOptativaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespAvalOptativaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.respAvalOptativa).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
