/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { HorarioMateriaUpdateComponent } from 'app/entities/horario-materia/horario-materia-update.component';
import { HorarioMateriaService } from 'app/entities/horario-materia/horario-materia.service';
import { HorarioMateria } from 'app/shared/model/horario-materia.model';

describe('Component Tests', () => {
    describe('HorarioMateria Management Update Component', () => {
        let comp: HorarioMateriaUpdateComponent;
        let fixture: ComponentFixture<HorarioMateriaUpdateComponent>;
        let service: HorarioMateriaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [HorarioMateriaUpdateComponent]
            })
                .overrideTemplate(HorarioMateriaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HorarioMateriaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HorarioMateriaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new HorarioMateria(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.horarioMateria = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new HorarioMateria();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.horarioMateria = entity;
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
