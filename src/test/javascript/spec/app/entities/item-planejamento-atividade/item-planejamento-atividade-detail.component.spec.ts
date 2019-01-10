/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemPlanejamentoAtividadeDetailComponent } from 'app/entities/item-planejamento-atividade/item-planejamento-atividade-detail.component';
import { ItemPlanejamentoAtividade } from 'app/shared/model/item-planejamento-atividade.model';

describe('Component Tests', () => {
    describe('ItemPlanejamentoAtividade Management Detail Component', () => {
        let comp: ItemPlanejamentoAtividadeDetailComponent;
        let fixture: ComponentFixture<ItemPlanejamentoAtividadeDetailComponent>;
        const route = ({ data: of({ itemPlanejamentoAtividade: new ItemPlanejamentoAtividade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemPlanejamentoAtividadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ItemPlanejamentoAtividadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemPlanejamentoAtividadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.itemPlanejamentoAtividade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
