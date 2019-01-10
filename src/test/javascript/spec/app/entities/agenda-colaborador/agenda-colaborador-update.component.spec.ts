/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AgendaColaboradorUpdateComponent } from 'app/entities/agenda-colaborador/agenda-colaborador-update.component';
import { AgendaColaboradorService } from 'app/entities/agenda-colaborador/agenda-colaborador.service';
import { AgendaColaborador } from 'app/shared/model/agenda-colaborador.model';

describe('Component Tests', () => {
    describe('AgendaColaborador Management Update Component', () => {
        let comp: AgendaColaboradorUpdateComponent;
        let fixture: ComponentFixture<AgendaColaboradorUpdateComponent>;
        let service: AgendaColaboradorService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AgendaColaboradorUpdateComponent]
            })
                .overrideTemplate(AgendaColaboradorUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AgendaColaboradorUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AgendaColaboradorService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AgendaColaborador(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.agendaColaborador = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AgendaColaborador();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.agendaColaborador = entity;
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
