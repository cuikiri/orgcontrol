/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoBiotipoUpdateComponent } from 'app/entities/tipo-biotipo/tipo-biotipo-update.component';
import { TipoBiotipoService } from 'app/entities/tipo-biotipo/tipo-biotipo.service';
import { TipoBiotipo } from 'app/shared/model/tipo-biotipo.model';

describe('Component Tests', () => {
    describe('TipoBiotipo Management Update Component', () => {
        let comp: TipoBiotipoUpdateComponent;
        let fixture: ComponentFixture<TipoBiotipoUpdateComponent>;
        let service: TipoBiotipoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoBiotipoUpdateComponent]
            })
                .overrideTemplate(TipoBiotipoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoBiotipoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoBiotipoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoBiotipo(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoBiotipo = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoBiotipo();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoBiotipo = entity;
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
