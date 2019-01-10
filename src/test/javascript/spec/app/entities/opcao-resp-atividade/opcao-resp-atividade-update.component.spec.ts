/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { OpcaoRespAtividadeUpdateComponent } from 'app/entities/opcao-resp-atividade/opcao-resp-atividade-update.component';
import { OpcaoRespAtividadeService } from 'app/entities/opcao-resp-atividade/opcao-resp-atividade.service';
import { OpcaoRespAtividade } from 'app/shared/model/opcao-resp-atividade.model';

describe('Component Tests', () => {
    describe('OpcaoRespAtividade Management Update Component', () => {
        let comp: OpcaoRespAtividadeUpdateComponent;
        let fixture: ComponentFixture<OpcaoRespAtividadeUpdateComponent>;
        let service: OpcaoRespAtividadeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [OpcaoRespAtividadeUpdateComponent]
            })
                .overrideTemplate(OpcaoRespAtividadeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OpcaoRespAtividadeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OpcaoRespAtividadeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new OpcaoRespAtividade(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.opcaoRespAtividade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new OpcaoRespAtividade();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.opcaoRespAtividade = entity;
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
