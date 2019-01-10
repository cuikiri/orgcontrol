/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PlanejamentoAtividadeUpdateComponent } from 'app/entities/planejamento-atividade/planejamento-atividade-update.component';
import { PlanejamentoAtividadeService } from 'app/entities/planejamento-atividade/planejamento-atividade.service';
import { PlanejamentoAtividade } from 'app/shared/model/planejamento-atividade.model';

describe('Component Tests', () => {
    describe('PlanejamentoAtividade Management Update Component', () => {
        let comp: PlanejamentoAtividadeUpdateComponent;
        let fixture: ComponentFixture<PlanejamentoAtividadeUpdateComponent>;
        let service: PlanejamentoAtividadeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PlanejamentoAtividadeUpdateComponent]
            })
                .overrideTemplate(PlanejamentoAtividadeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanejamentoAtividadeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanejamentoAtividadeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanejamentoAtividade(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planejamentoAtividade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanejamentoAtividade();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planejamentoAtividade = entity;
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
