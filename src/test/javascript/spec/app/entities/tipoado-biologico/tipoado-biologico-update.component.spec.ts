/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoadoBiologicoUpdateComponent } from 'app/entities/tipoado-biologico/tipoado-biologico-update.component';
import { TipoadoBiologicoService } from 'app/entities/tipoado-biologico/tipoado-biologico.service';
import { TipoadoBiologico } from 'app/shared/model/tipoado-biologico.model';

describe('Component Tests', () => {
    describe('TipoadoBiologico Management Update Component', () => {
        let comp: TipoadoBiologicoUpdateComponent;
        let fixture: ComponentFixture<TipoadoBiologicoUpdateComponent>;
        let service: TipoadoBiologicoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoadoBiologicoUpdateComponent]
            })
                .overrideTemplate(TipoadoBiologicoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoadoBiologicoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoadoBiologicoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoadoBiologico(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoadoBiologico = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipoadoBiologico();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipoadoBiologico = entity;
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
