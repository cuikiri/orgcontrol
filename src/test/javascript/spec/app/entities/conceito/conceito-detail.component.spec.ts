/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ConceitoDetailComponent } from 'app/entities/conceito/conceito-detail.component';
import { Conceito } from 'app/shared/model/conceito.model';

describe('Component Tests', () => {
    describe('Conceito Management Detail Component', () => {
        let comp: ConceitoDetailComponent;
        let fixture: ComponentFixture<ConceitoDetailComponent>;
        const route = ({ data: of({ conceito: new Conceito(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ConceitoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ConceitoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConceitoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.conceito).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
