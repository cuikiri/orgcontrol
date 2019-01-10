/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DiaNaoUtilUpdateComponent } from 'app/entities/dia-nao-util/dia-nao-util-update.component';
import { DiaNaoUtilService } from 'app/entities/dia-nao-util/dia-nao-util.service';
import { DiaNaoUtil } from 'app/shared/model/dia-nao-util.model';

describe('Component Tests', () => {
    describe('DiaNaoUtil Management Update Component', () => {
        let comp: DiaNaoUtilUpdateComponent;
        let fixture: ComponentFixture<DiaNaoUtilUpdateComponent>;
        let service: DiaNaoUtilService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DiaNaoUtilUpdateComponent]
            })
                .overrideTemplate(DiaNaoUtilUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DiaNaoUtilUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DiaNaoUtilService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new DiaNaoUtil(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.diaNaoUtil = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new DiaNaoUtil();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.diaNaoUtil = entity;
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
