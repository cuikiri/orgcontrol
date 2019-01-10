/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { OpcaoRespAvalOptativaUpdateComponent } from 'app/entities/opcao-resp-aval-optativa/opcao-resp-aval-optativa-update.component';
import { OpcaoRespAvalOptativaService } from 'app/entities/opcao-resp-aval-optativa/opcao-resp-aval-optativa.service';
import { OpcaoRespAvalOptativa } from 'app/shared/model/opcao-resp-aval-optativa.model';

describe('Component Tests', () => {
    describe('OpcaoRespAvalOptativa Management Update Component', () => {
        let comp: OpcaoRespAvalOptativaUpdateComponent;
        let fixture: ComponentFixture<OpcaoRespAvalOptativaUpdateComponent>;
        let service: OpcaoRespAvalOptativaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [OpcaoRespAvalOptativaUpdateComponent]
            })
                .overrideTemplate(OpcaoRespAvalOptativaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OpcaoRespAvalOptativaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OpcaoRespAvalOptativaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new OpcaoRespAvalOptativa(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.opcaoRespAvalOptativa = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new OpcaoRespAvalOptativa();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.opcaoRespAvalOptativa = entity;
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
