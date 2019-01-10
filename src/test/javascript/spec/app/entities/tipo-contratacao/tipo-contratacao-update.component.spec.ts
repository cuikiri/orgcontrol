/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoContratacaoUpdateComponent } from 'app/entities/tipo-contratacao/tipo-contratacao-update.component';
import { TipoContratacaoService } from 'app/entities/tipo-contratacao/tipo-contratacao.service';
import { TipoContratacao } from 'app/shared/model/tipo-contratacao.model';

describe('Component Tests', () => {
    describe('TipoContratacao Management Update Component', () => {
        let comp: TipoContratacaoUpdateComponent;
        let fixture: ComponentFixture<TipoContratacaoUpdateComponent>;
        let service: TipoContratacaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoContratacaoUpdateComponent]
            })
                .overrideTemplate(TipoContratacaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoContratacaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoContratacaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoContratacao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoContratacao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoContratacao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoContratacao = entity;
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
