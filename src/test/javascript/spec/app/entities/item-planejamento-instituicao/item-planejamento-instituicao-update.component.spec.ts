/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ItemPlanejamentoInstituicaoUpdateComponent } from 'app/entities/item-planejamento-instituicao/item-planejamento-instituicao-update.component';
import { ItemPlanejamentoInstituicaoService } from 'app/entities/item-planejamento-instituicao/item-planejamento-instituicao.service';
import { ItemPlanejamentoInstituicao } from 'app/shared/model/item-planejamento-instituicao.model';

describe('Component Tests', () => {
    describe('ItemPlanejamentoInstituicao Management Update Component', () => {
        let comp: ItemPlanejamentoInstituicaoUpdateComponent;
        let fixture: ComponentFixture<ItemPlanejamentoInstituicaoUpdateComponent>;
        let service: ItemPlanejamentoInstituicaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ItemPlanejamentoInstituicaoUpdateComponent]
            })
                .overrideTemplate(ItemPlanejamentoInstituicaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ItemPlanejamentoInstituicaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemPlanejamentoInstituicaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemPlanejamentoInstituicao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemPlanejamentoInstituicao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ItemPlanejamentoInstituicao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.itemPlanejamentoInstituicao = entity;
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
