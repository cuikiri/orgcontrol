/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemPlanejamentoInstituicaoDetailComponent } from 'app/entities/item-planejamento-instituicao/item-planejamento-instituicao-detail.component';
import { ItemPlanejamentoInstituicao } from 'app/shared/model/item-planejamento-instituicao.model';

describe('Component Tests', () => {
    describe('ItemPlanejamentoInstituicao Management Detail Component', () => {
        let comp: ItemPlanejamentoInstituicaoDetailComponent;
        let fixture: ComponentFixture<ItemPlanejamentoInstituicaoDetailComponent>;
        const route = ({ data: of({ itemPlanejamentoInstituicao: new ItemPlanejamentoInstituicao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemPlanejamentoInstituicaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ItemPlanejamentoInstituicaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemPlanejamentoInstituicaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.itemPlanejamentoInstituicao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
