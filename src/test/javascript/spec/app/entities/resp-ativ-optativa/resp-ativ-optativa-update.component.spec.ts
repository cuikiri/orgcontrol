/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAtivOptativaUpdateComponent } from 'app/entities/resp-ativ-optativa/resp-ativ-optativa-update.component';
import { RespAtivOptativaService } from 'app/entities/resp-ativ-optativa/resp-ativ-optativa.service';
import { RespAtivOptativa } from 'app/shared/model/resp-ativ-optativa.model';

describe('Component Tests', () => {
    describe('RespAtivOptativa Management Update Component', () => {
        let comp: RespAtivOptativaUpdateComponent;
        let fixture: ComponentFixture<RespAtivOptativaUpdateComponent>;
        let service: RespAtivOptativaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAtivOptativaUpdateComponent]
            })
                .overrideTemplate(RespAtivOptativaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RespAtivOptativaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespAtivOptativaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAtivOptativa(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAtivOptativa = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAtivOptativa();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAtivOptativa = entity;
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
