/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PlanejamentoEnsinoUpdateComponent } from 'app/entities/planejamento-ensino/planejamento-ensino-update.component';
import { PlanejamentoEnsinoService } from 'app/entities/planejamento-ensino/planejamento-ensino.service';
import { PlanejamentoEnsino } from 'app/shared/model/planejamento-ensino.model';

describe('Component Tests', () => {
    describe('PlanejamentoEnsino Management Update Component', () => {
        let comp: PlanejamentoEnsinoUpdateComponent;
        let fixture: ComponentFixture<PlanejamentoEnsinoUpdateComponent>;
        let service: PlanejamentoEnsinoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PlanejamentoEnsinoUpdateComponent]
            })
                .overrideTemplate(PlanejamentoEnsinoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanejamentoEnsinoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanejamentoEnsinoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanejamentoEnsino(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planejamentoEnsino = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanejamentoEnsino();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planejamentoEnsino = entity;
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
