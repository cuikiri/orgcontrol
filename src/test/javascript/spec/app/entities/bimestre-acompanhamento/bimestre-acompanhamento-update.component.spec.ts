/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { BimestreAcompanhamentoUpdateComponent } from 'app/entities/bimestre-acompanhamento/bimestre-acompanhamento-update.component';
import { BimestreAcompanhamentoService } from 'app/entities/bimestre-acompanhamento/bimestre-acompanhamento.service';
import { BimestreAcompanhamento } from 'app/shared/model/bimestre-acompanhamento.model';

describe('Component Tests', () => {
    describe('BimestreAcompanhamento Management Update Component', () => {
        let comp: BimestreAcompanhamentoUpdateComponent;
        let fixture: ComponentFixture<BimestreAcompanhamentoUpdateComponent>;
        let service: BimestreAcompanhamentoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [BimestreAcompanhamentoUpdateComponent]
            })
                .overrideTemplate(BimestreAcompanhamentoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BimestreAcompanhamentoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BimestreAcompanhamentoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new BimestreAcompanhamento(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bimestreAcompanhamento = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new BimestreAcompanhamento();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bimestreAcompanhamento = entity;
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
