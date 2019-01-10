/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoConceitoUpdateComponent } from 'app/entities/tipo-conceito/tipo-conceito-update.component';
import { TipoConceitoService } from 'app/entities/tipo-conceito/tipo-conceito.service';
import { TipoConceito } from 'app/shared/model/tipo-conceito.model';

describe('Component Tests', () => {
    describe('TipoConceito Management Update Component', () => {
        let comp: TipoConceitoUpdateComponent;
        let fixture: ComponentFixture<TipoConceitoUpdateComponent>;
        let service: TipoConceitoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoConceitoUpdateComponent]
            })
                .overrideTemplate(TipoConceitoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoConceitoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoConceitoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoConceito(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoConceito = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoConceito();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoConceito = entity;
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
