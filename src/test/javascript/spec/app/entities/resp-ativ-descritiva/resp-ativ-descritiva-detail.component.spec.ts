/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAtivDescritivaDetailComponent } from 'app/entities/resp-ativ-descritiva/resp-ativ-descritiva-detail.component';
import { RespAtivDescritiva } from 'app/shared/model/resp-ativ-descritiva.model';

describe('Component Tests', () => {
    describe('RespAtivDescritiva Management Detail Component', () => {
        let comp: RespAtivDescritivaDetailComponent;
        let fixture: ComponentFixture<RespAtivDescritivaDetailComponent>;
        const route = ({ data: of({ respAtivDescritiva: new RespAtivDescritiva(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAtivDescritivaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RespAtivDescritivaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespAtivDescritivaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.respAtivDescritiva).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
