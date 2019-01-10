/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { LocomocaoUpdateComponent } from 'app/entities/locomocao/locomocao-update.component';
import { LocomocaoService } from 'app/entities/locomocao/locomocao.service';
import { Locomocao } from 'app/shared/model/locomocao.model';

describe('Component Tests', () => {
    describe('Locomocao Management Update Component', () => {
        let comp: LocomocaoUpdateComponent;
        let fixture: ComponentFixture<LocomocaoUpdateComponent>;
        let service: LocomocaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [LocomocaoUpdateComponent]
            })
                .overrideTemplate(LocomocaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LocomocaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LocomocaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Locomocao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.locomocao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Locomocao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.locomocao = entity;
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
