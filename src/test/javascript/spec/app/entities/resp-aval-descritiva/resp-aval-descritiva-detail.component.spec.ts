/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAvalDescritivaDetailComponent } from 'app/entities/resp-aval-descritiva/resp-aval-descritiva-detail.component';
import { RespAvalDescritiva } from 'app/shared/model/resp-aval-descritiva.model';

describe('Component Tests', () => {
    describe('RespAvalDescritiva Management Detail Component', () => {
        let comp: RespAvalDescritivaDetailComponent;
        let fixture: ComponentFixture<RespAvalDescritivaDetailComponent>;
        const route = ({ data: of({ respAvalDescritiva: new RespAvalDescritiva(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAvalDescritivaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RespAvalDescritivaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespAvalDescritivaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.respAvalDescritiva).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
