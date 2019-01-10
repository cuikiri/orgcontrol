/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DadosMedicoUpdateComponent } from 'app/entities/dados-medico/dados-medico-update.component';
import { DadosMedicoService } from 'app/entities/dados-medico/dados-medico.service';
import { DadosMedico } from 'app/shared/model/dados-medico.model';

describe('Component Tests', () => {
    describe('DadosMedico Management Update Component', () => {
        let comp: DadosMedicoUpdateComponent;
        let fixture: ComponentFixture<DadosMedicoUpdateComponent>;
        let service: DadosMedicoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DadosMedicoUpdateComponent]
            })
                .overrideTemplate(DadosMedicoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DadosMedicoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DadosMedicoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new DadosMedico(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dadosMedico = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new DadosMedico();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dadosMedico = entity;
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
