/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { GeneralidadeUpdateComponent } from 'app/entities/generalidade/generalidade-update.component';
import { GeneralidadeService } from 'app/entities/generalidade/generalidade.service';
import { Generalidade } from 'app/shared/model/generalidade.model';

describe('Component Tests', () => {
    describe('Generalidade Management Update Component', () => {
        let comp: GeneralidadeUpdateComponent;
        let fixture: ComponentFixture<GeneralidadeUpdateComponent>;
        let service: GeneralidadeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [GeneralidadeUpdateComponent]
            })
                .overrideTemplate(GeneralidadeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GeneralidadeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GeneralidadeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Generalidade(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.generalidade = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Generalidade();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.generalidade = entity;
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
