/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemAcompanhamentoAtividadeUpdateComponent } from 'app/entities/item-acompanhamento-atividade/item-acompanhamento-atividade-update.component';
import { ItemAcompanhamentoAtividadeService } from 'app/entities/item-acompanhamento-atividade/item-acompanhamento-atividade.service';
import { ItemAcompanhamentoAtividade } from 'app/shared/model/item-acompanhamento-atividade.model';

describe('Component Tests', () => {
    describe('ItemAcompanhamentoAtividade Management Update Component', () => {
        let comp: ItemAcompanhamentoAtividadeUpdateComponent;
        let fixture: ComponentFixture<ItemAcompanhamentoAtividadeUpdateComponent>;
        let service: ItemAcompanhamentoAtividadeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemAcompanhamentoAtividadeUpdateComponent]
            })
                .overrideTemplate(ItemAcompanhamentoAtividadeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ItemAcompanhamentoAtividadeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemAcompanhamentoAtividadeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemAcompanhamentoAtividade(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemAcompanhamentoAtividade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemAcompanhamentoAtividade();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemAcompanhamentoAtividade = entity;
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
