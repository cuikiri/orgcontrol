/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { EnsinoUpdateComponent } from 'app/entities/ensino/ensino-update.component';
import { EnsinoService } from 'app/entities/ensino/ensino.service';
import { Ensino } from 'app/shared/model/ensino.model';

describe('Component Tests', () => {
    describe('Ensino Management Update Component', () => {
        let comp: EnsinoUpdateComponent;
        let fixture: ComponentFixture<EnsinoUpdateComponent>;
        let service: EnsinoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [EnsinoUpdateComponent]
            })
                .overrideTemplate(EnsinoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnsinoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnsinoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Ensino(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ensino = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Ensino();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ensino = entity;
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
