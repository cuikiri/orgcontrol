/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoParteBlocoUpdateComponent } from 'app/entities/tipo-parte-bloco/tipo-parte-bloco-update.component';
import { TipoParteBlocoService } from 'app/entities/tipo-parte-bloco/tipo-parte-bloco.service';
import { TipoParteBloco } from 'app/shared/model/tipo-parte-bloco.model';

describe('Component Tests', () => {
    describe('TipoParteBloco Management Update Component', () => {
        let comp: TipoParteBlocoUpdateComponent;
        let fixture: ComponentFixture<TipoParteBlocoUpdateComponent>;
        let service: TipoParteBlocoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoParteBlocoUpdateComponent]
            })
                .overrideTemplate(TipoParteBlocoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoParteBlocoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoParteBlocoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoParteBloco(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoParteBloco = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoParteBloco();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoParteBloco = entity;
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
