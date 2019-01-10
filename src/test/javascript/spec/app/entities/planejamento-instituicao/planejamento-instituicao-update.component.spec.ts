/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { PlanejamentoInstituicaoUpdateComponent } from 'app/entities/planejamento-instituicao/planejamento-instituicao-update.component';
import { PlanejamentoInstituicaoService } from 'app/entities/planejamento-instituicao/planejamento-instituicao.service';
import { PlanejamentoInstituicao } from 'app/shared/model/planejamento-instituicao.model';

describe('Component Tests', () => {
    describe('PlanejamentoInstituicao Management Update Component', () => {
        let comp: PlanejamentoInstituicaoUpdateComponent;
        let fixture: ComponentFixture<PlanejamentoInstituicaoUpdateComponent>;
        let service: PlanejamentoInstituicaoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [PlanejamentoInstituicaoUpdateComponent]
            })
                .overrideTemplate(PlanejamentoInstituicaoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanejamentoInstituicaoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanejamentoInstituicaoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanejamentoInstituicao(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planejamentoInstituicao = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanejamentoInstituicao();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planejamentoInstituicao = entity;
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
