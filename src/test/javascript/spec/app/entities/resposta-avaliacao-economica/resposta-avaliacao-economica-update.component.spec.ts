/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespostaAvaliacaoEconomicaUpdateComponent } from 'app/entities/resposta-avaliacao-economica/resposta-avaliacao-economica-update.component';
import { RespostaAvaliacaoEconomicaService } from 'app/entities/resposta-avaliacao-economica/resposta-avaliacao-economica.service';
import { RespostaAvaliacaoEconomica } from 'app/shared/model/resposta-avaliacao-economica.model';

describe('Component Tests', () => {
    describe('RespostaAvaliacaoEconomica Management Update Component', () => {
        let comp: RespostaAvaliacaoEconomicaUpdateComponent;
        let fixture: ComponentFixture<RespostaAvaliacaoEconomicaUpdateComponent>;
        let service: RespostaAvaliacaoEconomicaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespostaAvaliacaoEconomicaUpdateComponent]
            })
                .overrideTemplate(RespostaAvaliacaoEconomicaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RespostaAvaliacaoEconomicaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespostaAvaliacaoEconomicaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespostaAvaliacaoEconomica(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respostaAvaliacaoEconomica = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespostaAvaliacaoEconomica();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respostaAvaliacaoEconomica = entity;
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
