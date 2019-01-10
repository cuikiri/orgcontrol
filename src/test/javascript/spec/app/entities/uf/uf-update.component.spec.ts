/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { UfUpdateComponent } from 'app/entities/uf/uf-update.component';
import { UfService } from 'app/entities/uf/uf.service';
import { Uf } from 'app/shared/model/uf.model';

describe('Component Tests', () => {
    describe('Uf Management Update Component', () => {
        let comp: UfUpdateComponent;
        let fixture: ComponentFixture<UfUpdateComponent>;
        let service: UfService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [UfUpdateComponent]
            })
                .overrideTemplate(UfUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UfUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UfService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Uf(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.uf = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Uf();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.uf = entity;
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
