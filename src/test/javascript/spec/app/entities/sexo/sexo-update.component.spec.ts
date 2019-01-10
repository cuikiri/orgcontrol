/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { SexoUpdateComponent } from 'app/entities/sexo/sexo-update.component';
import { SexoService } from 'app/entities/sexo/sexo.service';
import { Sexo } from 'app/shared/model/sexo.model';

describe('Component Tests', () => {
    describe('Sexo Management Update Component', () => {
        let comp: SexoUpdateComponent;
        let fixture: ComponentFixture<SexoUpdateComponent>;
        let service: SexoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [SexoUpdateComponent]
            })
                .overrideTemplate(SexoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SexoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SexoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Sexo(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sexo = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Sexo();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sexo = entity;
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
