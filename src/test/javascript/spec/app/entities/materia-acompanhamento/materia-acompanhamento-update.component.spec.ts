/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { MateriaAcompanhamentoUpdateComponent } from 'app/entities/materia-acompanhamento/materia-acompanhamento-update.component';
import { MateriaAcompanhamentoService } from 'app/entities/materia-acompanhamento/materia-acompanhamento.service';
import { MateriaAcompanhamento } from 'app/shared/model/materia-acompanhamento.model';

describe('Component Tests', () => {
    describe('MateriaAcompanhamento Management Update Component', () => {
        let comp: MateriaAcompanhamentoUpdateComponent;
        let fixture: ComponentFixture<MateriaAcompanhamentoUpdateComponent>;
        let service: MateriaAcompanhamentoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [MateriaAcompanhamentoUpdateComponent]
            })
                .overrideTemplate(MateriaAcompanhamentoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MateriaAcompanhamentoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MateriaAcompanhamentoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new MateriaAcompanhamento(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.materiaAcompanhamento = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new MateriaAcompanhamento();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.materiaAcompanhamento = entity;
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
