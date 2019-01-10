/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAvalOptativaUpdateComponent } from 'app/entities/resp-aval-optativa/resp-aval-optativa-update.component';
import { RespAvalOptativaService } from 'app/entities/resp-aval-optativa/resp-aval-optativa.service';
import { RespAvalOptativa } from 'app/shared/model/resp-aval-optativa.model';

describe('Component Tests', () => {
    describe('RespAvalOptativa Management Update Component', () => {
        let comp: RespAvalOptativaUpdateComponent;
        let fixture: ComponentFixture<RespAvalOptativaUpdateComponent>;
        let service: RespAvalOptativaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAvalOptativaUpdateComponent]
            })
                .overrideTemplate(RespAvalOptativaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RespAvalOptativaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespAvalOptativaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAvalOptativa(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAvalOptativa = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAvalOptativa();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAvalOptativa = entity;
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
