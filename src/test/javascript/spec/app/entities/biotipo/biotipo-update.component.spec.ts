/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { BiotipoUpdateComponent } from 'app/entities/biotipo/biotipo-update.component';
import { BiotipoService } from 'app/entities/biotipo/biotipo.service';
import { Biotipo } from 'app/shared/model/biotipo.model';

describe('Component Tests', () => {
    describe('Biotipo Management Update Component', () => {
        let comp: BiotipoUpdateComponent;
        let fixture: ComponentFixture<BiotipoUpdateComponent>;
        let service: BiotipoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [BiotipoUpdateComponent]
            })
                .overrideTemplate(BiotipoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BiotipoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BiotipoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Biotipo(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.biotipo = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Biotipo();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.biotipo = entity;
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
