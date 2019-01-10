/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AcompanhamentoAtividadeUpdateComponent } from 'app/entities/acompanhamento-atividade/acompanhamento-atividade-update.component';
import { AcompanhamentoAtividadeService } from 'app/entities/acompanhamento-atividade/acompanhamento-atividade.service';
import { AcompanhamentoAtividade } from 'app/shared/model/acompanhamento-atividade.model';

describe('Component Tests', () => {
    describe('AcompanhamentoAtividade Management Update Component', () => {
        let comp: AcompanhamentoAtividadeUpdateComponent;
        let fixture: ComponentFixture<AcompanhamentoAtividadeUpdateComponent>;
        let service: AcompanhamentoAtividadeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AcompanhamentoAtividadeUpdateComponent]
            })
                .overrideTemplate(AcompanhamentoAtividadeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AcompanhamentoAtividadeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AcompanhamentoAtividadeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AcompanhamentoAtividade(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.acompanhamentoAtividade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AcompanhamentoAtividade();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.acompanhamentoAtividade = entity;
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
