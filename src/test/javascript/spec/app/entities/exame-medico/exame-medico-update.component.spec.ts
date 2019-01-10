/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ExameMedicoUpdateComponent } from 'app/entities/exame-medico/exame-medico-update.component';
import { ExameMedicoService } from 'app/entities/exame-medico/exame-medico.service';
import { ExameMedico } from 'app/shared/model/exame-medico.model';

describe('Component Tests', () => {
    describe('ExameMedico Management Update Component', () => {
        let comp: ExameMedicoUpdateComponent;
        let fixture: ComponentFixture<ExameMedicoUpdateComponent>;
        let service: ExameMedicoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ExameMedicoUpdateComponent]
            })
                .overrideTemplate(ExameMedicoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExameMedicoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExameMedicoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ExameMedico(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.exameMedico = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ExameMedico();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.exameMedico = entity;
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
