/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ChapaUpdateComponent } from 'app/entities/chapa/chapa-update.component';
import { ChapaService } from 'app/entities/chapa/chapa.service';
import { Chapa } from 'app/shared/model/chapa.model';

describe('Component Tests', () => {
    describe('Chapa Management Update Component', () => {
        let comp: ChapaUpdateComponent;
        let fixture: ComponentFixture<ChapaUpdateComponent>;
        let service: ChapaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ChapaUpdateComponent]
            })
                .overrideTemplate(ChapaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChapaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChapaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Chapa(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.chapa = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Chapa();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.chapa = entity;
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
