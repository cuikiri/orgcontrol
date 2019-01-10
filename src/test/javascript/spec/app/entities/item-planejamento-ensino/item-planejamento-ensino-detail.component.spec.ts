/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemPlanejamentoEnsinoDetailComponent } from 'app/entities/item-planejamento-ensino/item-planejamento-ensino-detail.component';
import { ItemPlanejamentoEnsino } from 'app/shared/model/item-planejamento-ensino.model';

describe('Component Tests', () => {
    describe('ItemPlanejamentoEnsino Management Detail Component', () => {
        let comp: ItemPlanejamentoEnsinoDetailComponent;
        let fixture: ComponentFixture<ItemPlanejamentoEnsinoDetailComponent>;
        const route = ({ data: of({ itemPlanejamentoEnsino: new ItemPlanejamentoEnsino(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemPlanejamentoEnsinoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ItemPlanejamentoEnsinoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemPlanejamentoEnsinoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.itemPlanejamentoEnsino).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
