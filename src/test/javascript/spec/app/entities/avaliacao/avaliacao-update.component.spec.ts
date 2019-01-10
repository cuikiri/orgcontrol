/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AvaliacaoUpdateComponent } from 'app/entities/avaliacao/avaliacao-update.component';
import { AvaliacaoService } from 'app/entities/avaliacao/avaliacao.service';
import { Avaliacao } from 'app/shared/model/avaliacao.model';

describe('Component Tests', () => {
    describe('Avaliacao Management Update Component', () => {
        let comp: AvaliacaoUpdateComponent;
        let fixture: ComponentFixture<AvaliacaoUpdateComponent>;
        let service: AvaliacaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AvaliacaoUpdateComponent]
            })
                .overrideTemplate(AvaliacaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AvaliacaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AvaliacaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Avaliacao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.avaliacao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Avaliacao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.avaliacao = entity;
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
