/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAvaliacaoEconomicaUpdateComponent } from 'app/entities/tipo-avaliacao-economica/tipo-avaliacao-economica-update.component';
import { TipoAvaliacaoEconomicaService } from 'app/entities/tipo-avaliacao-economica/tipo-avaliacao-economica.service';
import { TipoAvaliacaoEconomica } from 'app/shared/model/tipo-avaliacao-economica.model';

describe('Component Tests', () => {
    describe('TipoAvaliacaoEconomica Management Update Component', () => {
        let comp: TipoAvaliacaoEconomicaUpdateComponent;
        let fixture: ComponentFixture<TipoAvaliacaoEconomicaUpdateComponent>;
        let service: TipoAvaliacaoEconomicaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAvaliacaoEconomicaUpdateComponent]
            })
                .overrideTemplate(TipoAvaliacaoEconomicaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoAvaliacaoEconomicaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoAvaliacaoEconomicaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoAvaliacaoEconomica(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoAvaliacaoEconomica = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoAvaliacaoEconomica();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoAvaliacaoEconomica = entity;
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
