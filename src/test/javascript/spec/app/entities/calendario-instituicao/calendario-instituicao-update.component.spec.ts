/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { CalendarioInstituicaoUpdateComponent } from 'app/entities/calendario-instituicao/calendario-instituicao-update.component';
import { CalendarioInstituicaoService } from 'app/entities/calendario-instituicao/calendario-instituicao.service';
import { CalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';

describe('Component Tests', () => {
    describe('CalendarioInstituicao Management Update Component', () => {
        let comp: CalendarioInstituicaoUpdateComponent;
        let fixture: ComponentFixture<CalendarioInstituicaoUpdateComponent>;
        let service: CalendarioInstituicaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [CalendarioInstituicaoUpdateComponent]
            })
                .overrideTemplate(CalendarioInstituicaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CalendarioInstituicaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CalendarioInstituicaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CalendarioInstituicao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.calendarioInstituicao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CalendarioInstituicao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.calendarioInstituicao = entity;
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
