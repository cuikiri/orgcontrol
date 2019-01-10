/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAtivOptativaDetailComponent } from 'app/entities/resp-ativ-optativa/resp-ativ-optativa-detail.component';
import { RespAtivOptativa } from 'app/shared/model/resp-ativ-optativa.model';

describe('Component Tests', () => {
    describe('RespAtivOptativa Management Detail Component', () => {
        let comp: RespAtivOptativaDetailComponent;
        let fixture: ComponentFixture<RespAtivOptativaDetailComponent>;
        const route = ({ data: of({ respAtivOptativa: new RespAtivOptativa(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAtivOptativaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RespAtivOptativaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespAtivOptativaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.respAtivOptativa).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
