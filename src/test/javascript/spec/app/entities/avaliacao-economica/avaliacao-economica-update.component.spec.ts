/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AvaliacaoEconomicaUpdateComponent } from 'app/entities/avaliacao-economica/avaliacao-economica-update.component';
import { AvaliacaoEconomicaService } from 'app/entities/avaliacao-economica/avaliacao-economica.service';
import { AvaliacaoEconomica } from 'app/shared/model/avaliacao-economica.model';

describe('Component Tests', () => {
    describe('AvaliacaoEconomica Management Update Component', () => {
        let comp: AvaliacaoEconomicaUpdateComponent;
        let fixture: ComponentFixture<AvaliacaoEconomicaUpdateComponent>;
        let service: AvaliacaoEconomicaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AvaliacaoEconomicaUpdateComponent]
            })
                .overrideTemplate(AvaliacaoEconomicaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AvaliacaoEconomicaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AvaliacaoEconomicaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AvaliacaoEconomica(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.avaliacaoEconomica = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AvaliacaoEconomica();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.avaliacaoEconomica = entity;
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
