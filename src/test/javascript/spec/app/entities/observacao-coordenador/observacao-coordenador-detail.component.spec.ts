/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ObservacaoCoordenadorDetailComponent } from 'app/entities/observacao-coordenador/observacao-coordenador-detail.component';
import { ObservacaoCoordenador } from 'app/shared/model/observacao-coordenador.model';

describe('Component Tests', () => {
    describe('ObservacaoCoordenador Management Detail Component', () => {
        let comp: ObservacaoCoordenadorDetailComponent;
        let fixture: ComponentFixture<ObservacaoCoordenadorDetailComponent>;
        const route = ({ data: of({ observacaoCoordenador: new ObservacaoCoordenador(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ObservacaoCoordenadorDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ObservacaoCoordenadorDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ObservacaoCoordenadorDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.observacaoCoordenador).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
