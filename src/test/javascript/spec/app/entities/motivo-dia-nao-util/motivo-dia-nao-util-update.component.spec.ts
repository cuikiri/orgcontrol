/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { MotivoDiaNaoUtilUpdateComponent } from 'app/entities/motivo-dia-nao-util/motivo-dia-nao-util-update.component';
import { MotivoDiaNaoUtilService } from 'app/entities/motivo-dia-nao-util/motivo-dia-nao-util.service';
import { MotivoDiaNaoUtil } from 'app/shared/model/motivo-dia-nao-util.model';

describe('Component Tests', () => {
    describe('MotivoDiaNaoUtil Management Update Component', () => {
        let comp: MotivoDiaNaoUtilUpdateComponent;
        let fixture: ComponentFixture<MotivoDiaNaoUtilUpdateComponent>;
        let service: MotivoDiaNaoUtilService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [MotivoDiaNaoUtilUpdateComponent]
            })
                .overrideTemplate(MotivoDiaNaoUtilUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MotivoDiaNaoUtilUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MotivoDiaNaoUtilService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new MotivoDiaNaoUtil(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.motivoDiaNaoUtil = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new MotivoDiaNaoUtil();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.motivoDiaNaoUtil = entity;
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
