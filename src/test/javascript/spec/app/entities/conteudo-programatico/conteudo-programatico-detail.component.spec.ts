/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ConteudoProgramaticoDetailComponent } from 'app/entities/conteudo-programatico/conteudo-programatico-detail.component';
import { ConteudoProgramatico } from 'app/shared/model/conteudo-programatico.model';

describe('Component Tests', () => {
    describe('ConteudoProgramatico Management Detail Component', () => {
        let comp: ConteudoProgramaticoDetailComponent;
        let fixture: ComponentFixture<ConteudoProgramaticoDetailComponent>;
        const route = ({ data: of({ conteudoProgramatico: new ConteudoProgramatico(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ConteudoProgramaticoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ConteudoProgramaticoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConteudoProgramaticoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.conteudoProgramatico).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
