/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoUnidadeUpdateComponent } from 'app/entities/tipo-unidade/tipo-unidade-update.component';
import { TipoUnidadeService } from 'app/entities/tipo-unidade/tipo-unidade.service';
import { TipoUnidade } from 'app/shared/model/tipo-unidade.model';

describe('Component Tests', () => {
    describe('TipoUnidade Management Update Component', () => {
        let comp: TipoUnidadeUpdateComponent;
        let fixture: ComponentFixture<TipoUnidadeUpdateComponent>;
        let service: TipoUnidadeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoUnidadeUpdateComponent]
            })
                .overrideTemplate(TipoUnidadeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoUnidadeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoUnidadeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoUnidade(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoUnidade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoUnidade();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoUnidade = entity;
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
