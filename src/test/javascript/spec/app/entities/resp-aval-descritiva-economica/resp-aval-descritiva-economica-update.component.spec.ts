/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAvalDescritivaEconomicaUpdateComponent } from 'app/entities/resp-aval-descritiva-economica/resp-aval-descritiva-economica-update.component';
import { RespAvalDescritivaEconomicaService } from 'app/entities/resp-aval-descritiva-economica/resp-aval-descritiva-economica.service';
import { RespAvalDescritivaEconomica } from 'app/shared/model/resp-aval-descritiva-economica.model';

describe('Component Tests', () => {
    describe('RespAvalDescritivaEconomica Management Update Component', () => {
        let comp: RespAvalDescritivaEconomicaUpdateComponent;
        let fixture: ComponentFixture<RespAvalDescritivaEconomicaUpdateComponent>;
        let service: RespAvalDescritivaEconomicaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAvalDescritivaEconomicaUpdateComponent]
            })
                .overrideTemplate(RespAvalDescritivaEconomicaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RespAvalDescritivaEconomicaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespAvalDescritivaEconomicaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAvalDescritivaEconomica(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAvalDescritivaEconomica = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAvalDescritivaEconomica();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAvalDescritivaEconomica = entity;
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
