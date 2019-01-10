/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ParteBlocoUpdateComponent } from 'app/entities/parte-bloco/parte-bloco-update.component';
import { ParteBlocoService } from 'app/entities/parte-bloco/parte-bloco.service';
import { ParteBloco } from 'app/shared/model/parte-bloco.model';

describe('Component Tests', () => {
    describe('ParteBloco Management Update Component', () => {
        let comp: ParteBlocoUpdateComponent;
        let fixture: ComponentFixture<ParteBlocoUpdateComponent>;
        let service: ParteBlocoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ParteBlocoUpdateComponent]
            })
                .overrideTemplate(ParteBlocoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ParteBlocoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParteBlocoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParteBloco(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.parteBloco = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParteBloco();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.parteBloco = entity;
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
