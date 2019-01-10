/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemPlanejamentoEnsinoUpdateComponent } from 'app/entities/item-planejamento-ensino/item-planejamento-ensino-update.component';
import { ItemPlanejamentoEnsinoService } from 'app/entities/item-planejamento-ensino/item-planejamento-ensino.service';
import { ItemPlanejamentoEnsino } from 'app/shared/model/item-planejamento-ensino.model';

describe('Component Tests', () => {
    describe('ItemPlanejamentoEnsino Management Update Component', () => {
        let comp: ItemPlanejamentoEnsinoUpdateComponent;
        let fixture: ComponentFixture<ItemPlanejamentoEnsinoUpdateComponent>;
        let service: ItemPlanejamentoEnsinoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemPlanejamentoEnsinoUpdateComponent]
            })
                .overrideTemplate(ItemPlanejamentoEnsinoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ItemPlanejamentoEnsinoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemPlanejamentoEnsinoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemPlanejamentoEnsino(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemPlanejamentoEnsino = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemPlanejamentoEnsino();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemPlanejamentoEnsino = entity;
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
