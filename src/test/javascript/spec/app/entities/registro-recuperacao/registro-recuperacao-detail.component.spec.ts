/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { RegistroRecuperacaoDetailComponent } from 'app/entities/registro-recuperacao/registro-recuperacao-detail.component';
import { RegistroRecuperacao } from 'app/shared/model/registro-recuperacao.model';

describe('Component Tests', () => {
    describe('RegistroRecuperacao Management Detail Component', () => {
        let comp: RegistroRecuperacaoDetailComponent;
        let fixture: ComponentFixture<RegistroRecuperacaoDetailComponent>;
        const route = ({ data: of({ registroRecuperacao: new RegistroRecuperacao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RegistroRecuperacaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RegistroRecuperacaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RegistroRecuperacaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.registroRecuperacao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
