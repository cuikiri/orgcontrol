/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoBlocoUpdateComponent } from 'app/entities/tipo-bloco/tipo-bloco-update.component';
import { TipoBlocoService } from 'app/entities/tipo-bloco/tipo-bloco.service';
import { TipoBloco } from 'app/shared/model/tipo-bloco.model';

describe('Component Tests', () => {
    describe('TipoBloco Management Update Component', () => {
        let comp: TipoBlocoUpdateComponent;
        let fixture: ComponentFixture<TipoBlocoUpdateComponent>;
        let service: TipoBlocoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoBlocoUpdateComponent]
            })
                .overrideTemplate(TipoBlocoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoBlocoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoBlocoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoBloco(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoBloco = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoBloco();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoBloco = entity;
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
