/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ReligiaoUpdateComponent } from 'app/entities/religiao/religiao-update.component';
import { ReligiaoService } from 'app/entities/religiao/religiao.service';
import { Religiao } from 'app/shared/model/religiao.model';

describe('Component Tests', () => {
    describe('Religiao Management Update Component', () => {
        let comp: ReligiaoUpdateComponent;
        let fixture: ComponentFixture<ReligiaoUpdateComponent>;
        let service: ReligiaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ReligiaoUpdateComponent]
            })
                .overrideTemplate(ReligiaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ReligiaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReligiaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Religiao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.religiao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Religiao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.religiao = entity;
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
