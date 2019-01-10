/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemAvaliacaoDetailComponent } from 'app/entities/item-avaliacao/item-avaliacao-detail.component';
import { ItemAvaliacao } from 'app/shared/model/item-avaliacao.model';

describe('Component Tests', () => {
    describe('ItemAvaliacao Management Detail Component', () => {
        let comp: ItemAvaliacaoDetailComponent;
        let fixture: ComponentFixture<ItemAvaliacaoDetailComponent>;
        const route = ({ data: of({ itemAvaliacao: new ItemAvaliacao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemAvaliacaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ItemAvaliacaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemAvaliacaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.itemAvaliacao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
