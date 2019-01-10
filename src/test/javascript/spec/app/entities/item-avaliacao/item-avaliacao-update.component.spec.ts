/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemAvaliacaoUpdateComponent } from 'app/entities/item-avaliacao/item-avaliacao-update.component';
import { ItemAvaliacaoService } from 'app/entities/item-avaliacao/item-avaliacao.service';
import { ItemAvaliacao } from 'app/shared/model/item-avaliacao.model';

describe('Component Tests', () => {
    describe('ItemAvaliacao Management Update Component', () => {
        let comp: ItemAvaliacaoUpdateComponent;
        let fixture: ComponentFixture<ItemAvaliacaoUpdateComponent>;
        let service: ItemAvaliacaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemAvaliacaoUpdateComponent]
            })
                .overrideTemplate(ItemAvaliacaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ItemAvaliacaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemAvaliacaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemAvaliacao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemAvaliacao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemAvaliacao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemAvaliacao = entity;
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
