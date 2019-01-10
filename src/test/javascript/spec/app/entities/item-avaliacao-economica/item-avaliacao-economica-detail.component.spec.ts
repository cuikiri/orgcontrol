/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemAvaliacaoEconomicaDetailComponent } from 'app/entities/item-avaliacao-economica/item-avaliacao-economica-detail.component';
import { ItemAvaliacaoEconomica } from 'app/shared/model/item-avaliacao-economica.model';

describe('Component Tests', () => {
    describe('ItemAvaliacaoEconomica Management Detail Component', () => {
        let comp: ItemAvaliacaoEconomicaDetailComponent;
        let fixture: ComponentFixture<ItemAvaliacaoEconomicaDetailComponent>;
        const route = ({ data: of({ itemAvaliacaoEconomica: new ItemAvaliacaoEconomica(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemAvaliacaoEconomicaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ItemAvaliacaoEconomicaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemAvaliacaoEconomicaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.itemAvaliacaoEconomica).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
