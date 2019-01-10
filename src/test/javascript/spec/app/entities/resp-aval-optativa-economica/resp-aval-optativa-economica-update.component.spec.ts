/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespAvalOptativaEconomicaUpdateComponent } from 'app/entities/resp-aval-optativa-economica/resp-aval-optativa-economica-update.component';
import { RespAvalOptativaEconomicaService } from 'app/entities/resp-aval-optativa-economica/resp-aval-optativa-economica.service';
import { RespAvalOptativaEconomica } from 'app/shared/model/resp-aval-optativa-economica.model';

describe('Component Tests', () => {
    describe('RespAvalOptativaEconomica Management Update Component', () => {
        let comp: RespAvalOptativaEconomicaUpdateComponent;
        let fixture: ComponentFixture<RespAvalOptativaEconomicaUpdateComponent>;
        let service: RespAvalOptativaEconomicaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespAvalOptativaEconomicaUpdateComponent]
            })
                .overrideTemplate(RespAvalOptativaEconomicaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RespAvalOptativaEconomicaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespAvalOptativaEconomicaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAvalOptativaEconomica(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAvalOptativaEconomica = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespAvalOptativaEconomica();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respAvalOptativaEconomica = entity;
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
