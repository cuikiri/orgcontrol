/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { FechamentoBimestreUpdateComponent } from 'app/entities/fechamento-bimestre/fechamento-bimestre-update.component';
import { FechamentoBimestreService } from 'app/entities/fechamento-bimestre/fechamento-bimestre.service';
import { FechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';

describe('Component Tests', () => {
    describe('FechamentoBimestre Management Update Component', () => {
        let comp: FechamentoBimestreUpdateComponent;
        let fixture: ComponentFixture<FechamentoBimestreUpdateComponent>;
        let service: FechamentoBimestreService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [FechamentoBimestreUpdateComponent]
            })
                .overrideTemplate(FechamentoBimestreUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FechamentoBimestreUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FechamentoBimestreService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new FechamentoBimestre(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.fechamentoBimestre = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new FechamentoBimestre();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.fechamentoBimestre = entity;
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
