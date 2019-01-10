/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { OpcaoRespAvalOptativaEconomicaUpdateComponent } from 'app/entities/opcao-resp-aval-optativa-economica/opcao-resp-aval-optativa-economica-update.component';
import { OpcaoRespAvalOptativaEconomicaService } from 'app/entities/opcao-resp-aval-optativa-economica/opcao-resp-aval-optativa-economica.service';
import { OpcaoRespAvalOptativaEconomica } from 'app/shared/model/opcao-resp-aval-optativa-economica.model';

describe('Component Tests', () => {
    describe('OpcaoRespAvalOptativaEconomica Management Update Component', () => {
        let comp: OpcaoRespAvalOptativaEconomicaUpdateComponent;
        let fixture: ComponentFixture<OpcaoRespAvalOptativaEconomicaUpdateComponent>;
        let service: OpcaoRespAvalOptativaEconomicaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [OpcaoRespAvalOptativaEconomicaUpdateComponent]
            })
                .overrideTemplate(OpcaoRespAvalOptativaEconomicaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OpcaoRespAvalOptativaEconomicaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OpcaoRespAvalOptativaEconomicaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new OpcaoRespAvalOptativaEconomica(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.opcaoRespAvalOptativaEconomica = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new OpcaoRespAvalOptativaEconomica();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.opcaoRespAvalOptativaEconomica = entity;
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
