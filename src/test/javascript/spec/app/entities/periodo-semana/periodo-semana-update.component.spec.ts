/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PeriodoSemanaUpdateComponent } from 'app/entities/periodo-semana/periodo-semana-update.component';
import { PeriodoSemanaService } from 'app/entities/periodo-semana/periodo-semana.service';
import { PeriodoSemana } from 'app/shared/model/periodo-semana.model';

describe('Component Tests', () => {
    describe('PeriodoSemana Management Update Component', () => {
        let comp: PeriodoSemanaUpdateComponent;
        let fixture: ComponentFixture<PeriodoSemanaUpdateComponent>;
        let service: PeriodoSemanaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PeriodoSemanaUpdateComponent]
            })
                .overrideTemplate(PeriodoSemanaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PeriodoSemanaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PeriodoSemanaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PeriodoSemana(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.periodoSemana = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PeriodoSemana();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.periodoSemana = entity;
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
