/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { BlocoUpdateComponent } from 'app/entities/bloco/bloco-update.component';
import { BlocoService } from 'app/entities/bloco/bloco.service';
import { Bloco } from 'app/shared/model/bloco.model';

describe('Component Tests', () => {
    describe('Bloco Management Update Component', () => {
        let comp: BlocoUpdateComponent;
        let fixture: ComponentFixture<BlocoUpdateComponent>;
        let service: BlocoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [BlocoUpdateComponent]
            })
                .overrideTemplate(BlocoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BlocoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BlocoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Bloco(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bloco = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Bloco();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bloco = entity;
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
