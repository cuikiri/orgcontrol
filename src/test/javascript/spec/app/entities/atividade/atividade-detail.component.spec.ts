/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AtividadeDetailComponent } from 'app/entities/atividade/atividade-detail.component';
import { Atividade } from 'app/shared/model/atividade.model';

describe('Component Tests', () => {
    describe('Atividade Management Detail Component', () => {
        let comp: AtividadeDetailComponent;
        let fixture: ComponentFixture<AtividadeDetailComponent>;
        const route = ({ data: of({ atividade: new Atividade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AtividadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AtividadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AtividadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.atividade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
