/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ObservacaoCoordenadorUpdateComponent } from 'app/entities/observacao-coordenador/observacao-coordenador-update.component';
import { ObservacaoCoordenadorService } from 'app/entities/observacao-coordenador/observacao-coordenador.service';
import { ObservacaoCoordenador } from 'app/shared/model/observacao-coordenador.model';

describe('Component Tests', () => {
    describe('ObservacaoCoordenador Management Update Component', () => {
        let comp: ObservacaoCoordenadorUpdateComponent;
        let fixture: ComponentFixture<ObservacaoCoordenadorUpdateComponent>;
        let service: ObservacaoCoordenadorService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ObservacaoCoordenadorUpdateComponent]
            })
                .overrideTemplate(ObservacaoCoordenadorUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ObservacaoCoordenadorUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ObservacaoCoordenadorService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ObservacaoCoordenador(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.observacaoCoordenador = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ObservacaoCoordenador();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.observacaoCoordenador = entity;
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
