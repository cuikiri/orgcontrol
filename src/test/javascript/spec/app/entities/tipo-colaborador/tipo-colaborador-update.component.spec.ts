/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoColaboradorUpdateComponent } from 'app/entities/tipo-colaborador/tipo-colaborador-update.component';
import { TipoColaboradorService } from 'app/entities/tipo-colaborador/tipo-colaborador.service';
import { TipoColaborador } from 'app/shared/model/tipo-colaborador.model';

describe('Component Tests', () => {
    describe('TipoColaborador Management Update Component', () => {
        let comp: TipoColaboradorUpdateComponent;
        let fixture: ComponentFixture<TipoColaboradorUpdateComponent>;
        let service: TipoColaboradorService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoColaboradorUpdateComponent]
            })
                .overrideTemplate(TipoColaboradorUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoColaboradorUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoColaboradorService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoColaborador(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoColaborador = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoColaborador();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoColaborador = entity;
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
