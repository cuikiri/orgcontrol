/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DoencaUpdateComponent } from 'app/entities/doenca/doenca-update.component';
import { DoencaService } from 'app/entities/doenca/doenca.service';
import { Doenca } from 'app/shared/model/doenca.model';

describe('Component Tests', () => {
    describe('Doenca Management Update Component', () => {
        let comp: DoencaUpdateComponent;
        let fixture: ComponentFixture<DoencaUpdateComponent>;
        let service: DoencaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DoencaUpdateComponent]
            })
                .overrideTemplate(DoencaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DoencaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DoencaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Doenca(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.doenca = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Doenca();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.doenca = entity;
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
