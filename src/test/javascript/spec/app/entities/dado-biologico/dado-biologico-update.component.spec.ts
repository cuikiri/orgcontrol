/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DadoBiologicoUpdateComponent } from 'app/entities/dado-biologico/dado-biologico-update.component';
import { DadoBiologicoService } from 'app/entities/dado-biologico/dado-biologico.service';
import { DadoBiologico } from 'app/shared/model/dado-biologico.model';

describe('Component Tests', () => {
    describe('DadoBiologico Management Update Component', () => {
        let comp: DadoBiologicoUpdateComponent;
        let fixture: ComponentFixture<DadoBiologicoUpdateComponent>;
        let service: DadoBiologicoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DadoBiologicoUpdateComponent]
            })
                .overrideTemplate(DadoBiologicoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DadoBiologicoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DadoBiologicoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new DadoBiologico(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dadoBiologico = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new DadoBiologico();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dadoBiologico = entity;
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
