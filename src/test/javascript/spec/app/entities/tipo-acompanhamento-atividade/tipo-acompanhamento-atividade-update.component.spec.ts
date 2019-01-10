/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAcompanhamentoAtividadeUpdateComponent } from 'app/entities/tipo-acompanhamento-atividade/tipo-acompanhamento-atividade-update.component';
import { TipoAcompanhamentoAtividadeService } from 'app/entities/tipo-acompanhamento-atividade/tipo-acompanhamento-atividade.service';
import { TipoAcompanhamentoAtividade } from 'app/shared/model/tipo-acompanhamento-atividade.model';

describe('Component Tests', () => {
    describe('TipoAcompanhamentoAtividade Management Update Component', () => {
        let comp: TipoAcompanhamentoAtividadeUpdateComponent;
        let fixture: ComponentFixture<TipoAcompanhamentoAtividadeUpdateComponent>;
        let service: TipoAcompanhamentoAtividadeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAcompanhamentoAtividadeUpdateComponent]
            })
                .overrideTemplate(TipoAcompanhamentoAtividadeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoAcompanhamentoAtividadeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoAcompanhamentoAtividadeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoAcompanhamentoAtividade(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoAcompanhamentoAtividade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoAcompanhamentoAtividade();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoAcompanhamentoAtividade = entity;
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
