/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAtividadeUpdateComponent } from 'app/entities/tipo-atividade/tipo-atividade-update.component';
import { TipoAtividadeService } from 'app/entities/tipo-atividade/tipo-atividade.service';
import { TipoAtividade } from 'app/shared/model/tipo-atividade.model';

describe('Component Tests', () => {
    describe('TipoAtividade Management Update Component', () => {
        let comp: TipoAtividadeUpdateComponent;
        let fixture: ComponentFixture<TipoAtividadeUpdateComponent>;
        let service: TipoAtividadeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAtividadeUpdateComponent]
            })
                .overrideTemplate(TipoAtividadeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoAtividadeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoAtividadeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoAtividade(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoAtividade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoAtividade();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoAtividade = entity;
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
