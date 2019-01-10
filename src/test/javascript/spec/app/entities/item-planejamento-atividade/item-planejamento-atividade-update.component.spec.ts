/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemPlanejamentoAtividadeUpdateComponent } from 'app/entities/item-planejamento-atividade/item-planejamento-atividade-update.component';
import { ItemPlanejamentoAtividadeService } from 'app/entities/item-planejamento-atividade/item-planejamento-atividade.service';
import { ItemPlanejamentoAtividade } from 'app/shared/model/item-planejamento-atividade.model';

describe('Component Tests', () => {
    describe('ItemPlanejamentoAtividade Management Update Component', () => {
        let comp: ItemPlanejamentoAtividadeUpdateComponent;
        let fixture: ComponentFixture<ItemPlanejamentoAtividadeUpdateComponent>;
        let service: ItemPlanejamentoAtividadeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemPlanejamentoAtividadeUpdateComponent]
            })
                .overrideTemplate(ItemPlanejamentoAtividadeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ItemPlanejamentoAtividadeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemPlanejamentoAtividadeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemPlanejamentoAtividade(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemPlanejamentoAtividade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemPlanejamentoAtividade();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemPlanejamentoAtividade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
