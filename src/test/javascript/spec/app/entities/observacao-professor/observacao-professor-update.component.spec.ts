/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ObservacaoProfessorUpdateComponent } from 'app/entities/observacao-professor/observacao-professor-update.component';
import { ObservacaoProfessorService } from 'app/entities/observacao-professor/observacao-professor.service';
import { ObservacaoProfessor } from 'app/shared/model/observacao-professor.model';

describe('Component Tests', () => {
    describe('ObservacaoProfessor Management Update Component', () => {
        let comp: ObservacaoProfessorUpdateComponent;
        let fixture: ComponentFixture<ObservacaoProfessorUpdateComponent>;
        let service: ObservacaoProfessorService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ObservacaoProfessorUpdateComponent]
            })
                .overrideTemplate(ObservacaoProfessorUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ObservacaoProfessorUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ObservacaoProfessorService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ObservacaoProfessor(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.observacaoProfessor = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ObservacaoProfessor();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.observacaoProfessor = entity;
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
