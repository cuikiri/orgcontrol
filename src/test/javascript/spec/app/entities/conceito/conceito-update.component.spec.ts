/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ConceitoUpdateComponent } from 'app/entities/conceito/conceito-update.component';
import { ConceitoService } from 'app/entities/conceito/conceito.service';
import { Conceito } from 'app/shared/model/conceito.model';

describe('Component Tests', () => {
    describe('Conceito Management Update Component', () => {
        let comp: ConceitoUpdateComponent;
        let fixture: ComponentFixture<ConceitoUpdateComponent>;
        let service: ConceitoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ConceitoUpdateComponent]
            })
                .overrideTemplate(ConceitoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConceitoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConceitoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Conceito(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.conceito = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Conceito();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.conceito = entity;
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
