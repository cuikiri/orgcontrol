/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ProblemaFisicoUpdateComponent } from 'app/entities/problema-fisico/problema-fisico-update.component';
import { ProblemaFisicoService } from 'app/entities/problema-fisico/problema-fisico.service';
import { ProblemaFisico } from 'app/shared/model/problema-fisico.model';

describe('Component Tests', () => {
    describe('ProblemaFisico Management Update Component', () => {
        let comp: ProblemaFisicoUpdateComponent;
        let fixture: ComponentFixture<ProblemaFisicoUpdateComponent>;
        let service: ProblemaFisicoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ProblemaFisicoUpdateComponent]
            })
                .overrideTemplate(ProblemaFisicoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProblemaFisicoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProblemaFisicoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ProblemaFisico(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.problemaFisico = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ProblemaFisico();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.problemaFisico = entity;
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
