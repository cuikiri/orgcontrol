/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DadosMedicoDetailComponent } from 'app/entities/dados-medico/dados-medico-detail.component';
import { DadosMedico } from 'app/shared/model/dados-medico.model';

describe('Component Tests', () => {
    describe('DadosMedico Management Detail Component', () => {
        let comp: DadosMedicoDetailComponent;
        let fixture: ComponentFixture<DadosMedicoDetailComponent>;
        const route = ({ data: of({ dadosMedico: new DadosMedico(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DadosMedicoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DadosMedicoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DadosMedicoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.dadosMedico).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
