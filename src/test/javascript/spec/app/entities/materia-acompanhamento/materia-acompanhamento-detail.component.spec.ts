/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { MateriaAcompanhamentoDetailComponent } from 'app/entities/materia-acompanhamento/materia-acompanhamento-detail.component';
import { MateriaAcompanhamento } from 'app/shared/model/materia-acompanhamento.model';

describe('Component Tests', () => {
    describe('MateriaAcompanhamento Management Detail Component', () => {
        let comp: MateriaAcompanhamentoDetailComponent;
        let fixture: ComponentFixture<MateriaAcompanhamentoDetailComponent>;
        const route = ({ data: of({ materiaAcompanhamento: new MateriaAcompanhamento(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [MateriaAcompanhamentoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MateriaAcompanhamentoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MateriaAcompanhamentoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.materiaAcompanhamento).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
