/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AvaliacaoDetailComponent } from 'app/entities/avaliacao/avaliacao-detail.component';
import { Avaliacao } from 'app/shared/model/avaliacao.model';

describe('Component Tests', () => {
    describe('Avaliacao Management Detail Component', () => {
        let comp: AvaliacaoDetailComponent;
        let fixture: ComponentFixture<AvaliacaoDetailComponent>;
        const route = ({ data: of({ avaliacao: new Avaliacao(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AvaliacaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AvaliacaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AvaliacaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.avaliacao).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
