/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ConceitoAcompanhamentoUpdateComponent } from 'app/entities/conceito-acompanhamento/conceito-acompanhamento-update.component';
import { ConceitoAcompanhamentoService } from 'app/entities/conceito-acompanhamento/conceito-acompanhamento.service';
import { ConceitoAcompanhamento } from 'app/shared/model/conceito-acompanhamento.model';

describe('Component Tests', () => {
    describe('ConceitoAcompanhamento Management Update Component', () => {
        let comp: ConceitoAcompanhamentoUpdateComponent;
        let fixture: ComponentFixture<ConceitoAcompanhamentoUpdateComponent>;
        let service: ConceitoAcompanhamentoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ConceitoAcompanhamentoUpdateComponent]
            })
                .overrideTemplate(ConceitoAcompanhamentoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConceitoAcompanhamentoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConceitoAcompanhamentoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ConceitoAcompanhamento(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.conceitoAcompanhamento = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ConceitoAcompanhamento();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.conceitoAcompanhamento = entity;
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
