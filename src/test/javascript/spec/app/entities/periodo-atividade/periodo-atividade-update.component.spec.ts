/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PeriodoAtividadeUpdateComponent } from 'app/entities/periodo-atividade/periodo-atividade-update.component';
import { PeriodoAtividadeService } from 'app/entities/periodo-atividade/periodo-atividade.service';
import { PeriodoAtividade } from 'app/shared/model/periodo-atividade.model';

describe('Component Tests', () => {
    describe('PeriodoAtividade Management Update Component', () => {
        let comp: PeriodoAtividadeUpdateComponent;
        let fixture: ComponentFixture<PeriodoAtividadeUpdateComponent>;
        let service: PeriodoAtividadeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PeriodoAtividadeUpdateComponent]
            })
                .overrideTemplate(PeriodoAtividadeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PeriodoAtividadeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PeriodoAtividadeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PeriodoAtividade(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.periodoAtividade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PeriodoAtividade();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.periodoAtividade = entity;
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
