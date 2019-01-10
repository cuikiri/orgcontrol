/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { FotoDocumentoUpdateComponent } from 'app/entities/foto-documento/foto-documento-update.component';
import { FotoDocumentoService } from 'app/entities/foto-documento/foto-documento.service';
import { FotoDocumento } from 'app/shared/model/foto-documento.model';

describe('Component Tests', () => {
    describe('FotoDocumento Management Update Component', () => {
        let comp: FotoDocumentoUpdateComponent;
        let fixture: ComponentFixture<FotoDocumentoUpdateComponent>;
        let service: FotoDocumentoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [FotoDocumentoUpdateComponent]
            })
                .overrideTemplate(FotoDocumentoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FotoDocumentoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FotoDocumentoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new FotoDocumento(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.fotoDocumento = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new FotoDocumento();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.fotoDocumento = entity;
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
