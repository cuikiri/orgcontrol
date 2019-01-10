/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DiarioUpdateComponent } from 'app/entities/diario/diario-update.component';
import { DiarioService } from 'app/entities/diario/diario.service';
import { Diario } from 'app/shared/model/diario.model';

describe('Component Tests', () => {
    describe('Diario Management Update Component', () => {
        let comp: DiarioUpdateComponent;
        let fixture: ComponentFixture<DiarioUpdateComponent>;
        let service: DiarioService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DiarioUpdateComponent]
            })
                .overrideTemplate(DiarioUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DiarioUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DiarioService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Diario(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.diario = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Diario();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.diario = entity;
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
