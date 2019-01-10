/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DependenteLegalUpdateComponent } from 'app/entities/dependente-legal/dependente-legal-update.component';
import { DependenteLegalService } from 'app/entities/dependente-legal/dependente-legal.service';
import { DependenteLegal } from 'app/shared/model/dependente-legal.model';

describe('Component Tests', () => {
    describe('DependenteLegal Management Update Component', () => {
        let comp: DependenteLegalUpdateComponent;
        let fixture: ComponentFixture<DependenteLegalUpdateComponent>;
        let service: DependenteLegalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DependenteLegalUpdateComponent]
            })
                .overrideTemplate(DependenteLegalUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DependenteLegalUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DependenteLegalService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new DependenteLegal(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dependenteLegal = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new DependenteLegal();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dependenteLegal = entity;
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
