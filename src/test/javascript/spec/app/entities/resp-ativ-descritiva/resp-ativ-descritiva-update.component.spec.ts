/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAtivDescritivaUpdateComponent } from 'app/entities/resp-ativ-descritiva/resp-ativ-descritiva-update.component';
import { RespAtivDescritivaService } from 'app/entities/resp-ativ-descritiva/resp-ativ-descritiva.service';
import { RespAtivDescritiva } from 'app/shared/model/resp-ativ-descritiva.model';

describe('Component Tests', () => {
    describe('RespAtivDescritiva Management Update Component', () => {
        let comp: RespAtivDescritivaUpdateComponent;
        let fixture: ComponentFixture<RespAtivDescritivaUpdateComponent>;
        let service: RespAtivDescritivaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAtivDescritivaUpdateComponent]
            })
                .overrideTemplate(RespAtivDescritivaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RespAtivDescritivaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespAtivDescritivaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAtivDescritiva(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAtivDescritiva = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAtivDescritiva();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAtivDescritiva = entity;
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
