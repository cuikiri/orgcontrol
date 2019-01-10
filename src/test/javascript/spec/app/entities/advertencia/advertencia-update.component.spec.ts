/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AdvertenciaUpdateComponent } from 'app/entities/advertencia/advertencia-update.component';
import { AdvertenciaService } from 'app/entities/advertencia/advertencia.service';
import { Advertencia } from 'app/shared/model/advertencia.model';

describe('Component Tests', () => {
    describe('Advertencia Management Update Component', () => {
        let comp: AdvertenciaUpdateComponent;
        let fixture: ComponentFixture<AdvertenciaUpdateComponent>;
        let service: AdvertenciaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AdvertenciaUpdateComponent]
            })
                .overrideTemplate(AdvertenciaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdvertenciaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdvertenciaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Advertencia(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.advertencia = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Advertencia();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.advertencia = entity;
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
