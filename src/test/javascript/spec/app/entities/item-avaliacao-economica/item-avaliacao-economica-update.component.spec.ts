/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemAvaliacaoEconomicaUpdateComponent } from 'app/entities/item-avaliacao-economica/item-avaliacao-economica-update.component';
import { ItemAvaliacaoEconomicaService } from 'app/entities/item-avaliacao-economica/item-avaliacao-economica.service';
import { ItemAvaliacaoEconomica } from 'app/shared/model/item-avaliacao-economica.model';

describe('Component Tests', () => {
    describe('ItemAvaliacaoEconomica Management Update Component', () => {
        let comp: ItemAvaliacaoEconomicaUpdateComponent;
        let fixture: ComponentFixture<ItemAvaliacaoEconomicaUpdateComponent>;
        let service: ItemAvaliacaoEconomicaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemAvaliacaoEconomicaUpdateComponent]
            })
                .overrideTemplate(ItemAvaliacaoEconomicaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ItemAvaliacaoEconomicaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemAvaliacaoEconomicaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemAvaliacaoEconomica(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemAvaliacaoEconomica = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemAvaliacaoEconomica();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemAvaliacaoEconomica = entity;
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
