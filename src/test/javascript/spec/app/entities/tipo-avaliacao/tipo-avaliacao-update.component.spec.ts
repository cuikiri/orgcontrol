/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAvaliacaoUpdateComponent } from 'app/entities/tipo-avaliacao/tipo-avaliacao-update.component';
import { TipoAvaliacaoService } from 'app/entities/tipo-avaliacao/tipo-avaliacao.service';
import { TipoAvaliacao } from 'app/shared/model/tipo-avaliacao.model';

describe('Component Tests', () => {
    describe('TipoAvaliacao Management Update Component', () => {
        let comp: TipoAvaliacaoUpdateComponent;
        let fixture: ComponentFixture<TipoAvaliacaoUpdateComponent>;
        let service: TipoAvaliacaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAvaliacaoUpdateComponent]
            })
                .overrideTemplate(TipoAvaliacaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoAvaliacaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoAvaliacaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoAvaliacao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoAvaliacao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoAvaliacao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoAvaliacao = entity;
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
