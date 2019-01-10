/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { OpcaoRespAvalOptativaDetailComponent } from 'app/entities/opcao-resp-aval-optativa/opcao-resp-aval-optativa-detail.component';
import { OpcaoRespAvalOptativa } from 'app/shared/model/opcao-resp-aval-optativa.model';

describe('Component Tests', () => {
    describe('OpcaoRespAvalOptativa Management Detail Component', () => {
        let comp: OpcaoRespAvalOptativaDetailComponent;
        let fixture: ComponentFixture<OpcaoRespAvalOptativaDetailComponent>;
        const route = ({ data: of({ opcaoRespAvalOptativa: new OpcaoRespAvalOptativa(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [OpcaoRespAvalOptativaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OpcaoRespAvalOptativaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OpcaoRespAvalOptativaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.opcaoRespAvalOptativa).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
