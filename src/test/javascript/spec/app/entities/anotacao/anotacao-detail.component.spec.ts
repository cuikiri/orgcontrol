/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AnotacaoDetailComponent } from 'app/entities/anotacao/anotacao-detail.component';
import { Anotacao } from 'app/shared/model/anotacao.model';

describe('Component Tests', () => {
    describe('Anotacao Management Detail Component', () => {
        let comp: AnotacaoDetailComponent;
        let fixture: ComponentFixture<AnotacaoDetailComponent>;
        const route = ({ data: of({ anotacao: new Anotacao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AnotacaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AnotacaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AnotacaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.anotacao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
