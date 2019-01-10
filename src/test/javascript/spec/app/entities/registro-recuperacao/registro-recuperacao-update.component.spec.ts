/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RegistroRecuperacaoUpdateComponent } from 'app/entities/registro-recuperacao/registro-recuperacao-update.component';
import { RegistroRecuperacaoService } from 'app/entities/registro-recuperacao/registro-recuperacao.service';
import { RegistroRecuperacao } from 'app/shared/model/registro-recuperacao.model';

describe('Component Tests', () => {
    describe('RegistroRecuperacao Management Update Component', () => {
        let comp: RegistroRecuperacaoUpdateComponent;
        let fixture: ComponentFixture<RegistroRecuperacaoUpdateComponent>;
        let service: RegistroRecuperacaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RegistroRecuperacaoUpdateComponent]
            })
                .overrideTemplate(RegistroRecuperacaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RegistroRecuperacaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RegistroRecuperacaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RegistroRecuperacao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.registroRecuperacao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RegistroRecuperacao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.registroRecuperacao = entity;
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
