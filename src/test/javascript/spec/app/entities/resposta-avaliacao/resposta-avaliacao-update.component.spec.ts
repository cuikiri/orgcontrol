/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespostaAvaliacaoUpdateComponent } from 'app/entities/resposta-avaliacao/resposta-avaliacao-update.component';
import { RespostaAvaliacaoService } from 'app/entities/resposta-avaliacao/resposta-avaliacao.service';
import { RespostaAvaliacao } from 'app/shared/model/resposta-avaliacao.model';

describe('Component Tests', () => {
    describe('RespostaAvaliacao Management Update Component', () => {
        let comp: RespostaAvaliacaoUpdateComponent;
        let fixture: ComponentFixture<RespostaAvaliacaoUpdateComponent>;
        let service: RespostaAvaliacaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespostaAvaliacaoUpdateComponent]
            })
                .overrideTemplate(RespostaAvaliacaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RespostaAvaliacaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespostaAvaliacaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespostaAvaliacao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respostaAvaliacao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RespostaAvaliacao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.respostaAvaliacao = entity;
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
