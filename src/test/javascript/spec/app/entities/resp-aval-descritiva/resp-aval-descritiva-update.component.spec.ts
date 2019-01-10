/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAvalDescritivaUpdateComponent } from 'app/entities/resp-aval-descritiva/resp-aval-descritiva-update.component';
import { RespAvalDescritivaService } from 'app/entities/resp-aval-descritiva/resp-aval-descritiva.service';
import { RespAvalDescritiva } from 'app/shared/model/resp-aval-descritiva.model';

describe('Component Tests', () => {
    describe('RespAvalDescritiva Management Update Component', () => {
        let comp: RespAvalDescritivaUpdateComponent;
        let fixture: ComponentFixture<RespAvalDescritivaUpdateComponent>;
        let service: RespAvalDescritivaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAvalDescritivaUpdateComponent]
            })
                .overrideTemplate(RespAvalDescritivaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RespAvalDescritivaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespAvalDescritivaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAvalDescritiva(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAvalDescritiva = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAvalDescritiva();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAvalDescritiva = entity;
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
