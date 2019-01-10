/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { CalendarioInstituicaoDetailComponent } from 'app/entities/calendario-instituicao/calendario-instituicao-detail.component';
import { CalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';

describe('Component Tests', () => {
    describe('CalendarioInstituicao Management Detail Component', () => {
        let comp: CalendarioInstituicaoDetailComponent;
        let fixture: ComponentFixture<CalendarioInstituicaoDetailComponent>;
        const route = ({ data: of({ calendarioInstituicao: new CalendarioInstituicao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [CalendarioInstituicaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CalendarioInstituicaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CalendarioInstituicaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.calendarioInstituicao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
