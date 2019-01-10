/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespostaAtividadeUpdateComponent } from 'app/entities/resposta-atividade/resposta-atividade-update.component';
import { RespostaAtividadeService } from 'app/entities/resposta-atividade/resposta-atividade.service';
import { RespostaAtividade } from 'app/shared/model/resposta-atividade.model';

describe('Component Tests', () => {
    describe('RespostaAtividade Management Update Component', () => {
        let comp: RespostaAtividadeUpdateComponent;
        let fixture: ComponentFixture<RespostaAtividadeUpdateComponent>;
        let service: RespostaAtividadeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespostaAtividadeUpdateComponent]
            })
                .overrideTemplate(RespostaAtividadeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RespostaAtividadeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespostaAtividadeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespostaAtividade(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respostaAtividade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespostaAtividade();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respostaAtividade = entity;
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
