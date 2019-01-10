/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemAcompanhamentoAtividadeDetailComponent } from 'app/entities/item-acompanhamento-atividade/item-acompanhamento-atividade-detail.component';
import { ItemAcompanhamentoAtividade } from 'app/shared/model/item-acompanhamento-atividade.model';

describe('Component Tests', () => {
    describe('ItemAcompanhamentoAtividade Management Detail Component', () => {
        let comp: ItemAcompanhamentoAtividadeDetailComponent;
        let fixture: ComponentFixture<ItemAcompanhamentoAtividadeDetailComponent>;
        const route = ({ data: of({ itemAcompanhamentoAtividade: new ItemAcompanhamentoAtividade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemAcompanhamentoAtividadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ItemAcompanhamentoAtividadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ItemAcompanhamentoAtividadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.itemAcompanhamentoAtividade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
