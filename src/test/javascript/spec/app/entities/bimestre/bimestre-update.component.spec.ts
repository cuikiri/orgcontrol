/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { BimestreUpdateComponent } from 'app/entities/bimestre/bimestre-update.component';
import { BimestreService } from 'app/entities/bimestre/bimestre.service';
import { Bimestre } from 'app/shared/model/bimestre.model';

describe('Component Tests', () => {
    describe('Bimestre Management Update Component', () => {
        let comp: BimestreUpdateComponent;
        let fixture: ComponentFixture<BimestreUpdateComponent>;
        let service: BimestreService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [BimestreUpdateComponent]
            })
                .overrideTemplate(BimestreUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BimestreUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BimestreService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Bimestre(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bimestre = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Bimestre();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bimestre = entity;
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
