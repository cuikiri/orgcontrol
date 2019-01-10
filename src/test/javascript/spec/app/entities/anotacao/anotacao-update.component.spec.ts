/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AnotacaoUpdateComponent } from 'app/entities/anotacao/anotacao-update.component';
import { AnotacaoService } from 'app/entities/anotacao/anotacao.service';
import { Anotacao } from 'app/shared/model/anotacao.model';

describe('Component Tests', () => {
    describe('Anotacao Management Update Component', () => {
        let comp: AnotacaoUpdateComponent;
        let fixture: ComponentFixture<AnotacaoUpdateComponent>;
        let service: AnotacaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AnotacaoUpdateComponent]
            })
                .overrideTemplate(AnotacaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AnotacaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AnotacaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Anotacao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.anotacao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Anotacao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.anotacao = entity;
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
