/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoAtividadeDetailComponent } from 'app/entities/tipo-atividade/tipo-atividade-detail.component';
import { TipoAtividade } from 'app/shared/model/tipo-atividade.model';

describe('Component Tests', () => {
    describe('TipoAtividade Management Detail Component', () => {
        let comp: TipoAtividadeDetailComponent;
        let fixture: ComponentFixture<TipoAtividadeDetailComponent>;
        const route = ({ data: of({ tipoAtividade: new TipoAtividade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoAtividadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoAtividadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoAtividadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoAtividade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
