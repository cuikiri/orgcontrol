/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { BimestreAcompanhamentoDetailComponent } from 'app/entities/bimestre-acompanhamento/bimestre-acompanhamento-detail.component';
import { BimestreAcompanhamento } from 'app/shared/model/bimestre-acompanhamento.model';

describe('Component Tests', () => {
    describe('BimestreAcompanhamento Management Detail Component', () => {
        let comp: BimestreAcompanhamentoDetailComponent;
        let fixture: ComponentFixture<BimestreAcompanhamentoDetailComponent>;
        const route = ({ data: of({ bimestreAcompanhamento: new BimestreAcompanhamento(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [BimestreAcompanhamentoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BimestreAcompanhamentoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BimestreAcompanhamentoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.bimestreAcompanhamento).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
