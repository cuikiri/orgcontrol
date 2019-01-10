/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { CaracteristicasPsiquicasUpdateComponent } from 'app/entities/caracteristicas-psiquicas/caracteristicas-psiquicas-update.component';
import { CaracteristicasPsiquicasService } from 'app/entities/caracteristicas-psiquicas/caracteristicas-psiquicas.service';
import { CaracteristicasPsiquicas } from 'app/shared/model/caracteristicas-psiquicas.model';

describe('Component Tests', () => {
    describe('CaracteristicasPsiquicas Management Update Component', () => {
        let comp: CaracteristicasPsiquicasUpdateComponent;
        let fixture: ComponentFixture<CaracteristicasPsiquicasUpdateComponent>;
        let service: CaracteristicasPsiquicasService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [CaracteristicasPsiquicasUpdateComponent]
            })
                .overrideTemplate(CaracteristicasPsiquicasUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CaracteristicasPsiquicasUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CaracteristicasPsiquicasService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CaracteristicasPsiquicas(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.caracteristicasPsiquicas = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CaracteristicasPsiquicas();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.caracteristicasPsiquicas = entity;
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
