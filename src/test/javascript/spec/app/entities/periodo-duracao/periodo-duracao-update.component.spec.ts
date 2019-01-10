/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PeriodoDuracaoUpdateComponent } from 'app/entities/periodo-duracao/periodo-duracao-update.component';
import { PeriodoDuracaoService } from 'app/entities/periodo-duracao/periodo-duracao.service';
import { PeriodoDuracao } from 'app/shared/model/periodo-duracao.model';

describe('Component Tests', () => {
    describe('PeriodoDuracao Management Update Component', () => {
        let comp: PeriodoDuracaoUpdateComponent;
        let fixture: ComponentFixture<PeriodoDuracaoUpdateComponent>;
        let service: PeriodoDuracaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PeriodoDuracaoUpdateComponent]
            })
                .overrideTemplate(PeriodoDuracaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PeriodoDuracaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PeriodoDuracaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PeriodoDuracao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.periodoDuracao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PeriodoDuracao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.periodoDuracao = entity;
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
