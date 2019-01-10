/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ConteudoProgramaticoUpdateComponent } from 'app/entities/conteudo-programatico/conteudo-programatico-update.component';
import { ConteudoProgramaticoService } from 'app/entities/conteudo-programatico/conteudo-programatico.service';
import { ConteudoProgramatico } from 'app/shared/model/conteudo-programatico.model';

describe('Component Tests', () => {
    describe('ConteudoProgramatico Management Update Component', () => {
        let comp: ConteudoProgramaticoUpdateComponent;
        let fixture: ComponentFixture<ConteudoProgramaticoUpdateComponent>;
        let service: ConteudoProgramaticoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ConteudoProgramaticoUpdateComponent]
            })
                .overrideTemplate(ConteudoProgramaticoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConteudoProgramaticoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConteudoProgramaticoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ConteudoProgramatico(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.conteudoProgramatico = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ConteudoProgramatico();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.conteudoProgramatico = entity;
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
