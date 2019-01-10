/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ChapaDetailComponent } from 'app/entities/chapa/chapa-detail.component';
import { Chapa } from 'app/shared/model/chapa.model';

describe('Component Tests', () => {
    describe('Chapa Management Detail Component', () => {
        let comp: ChapaDetailComponent;
        let fixture: ComponentFixture<ChapaDetailComponent>;
        const route = ({ data: of({ chapa: new Chapa(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ChapaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ChapaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ChapaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.chapa).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
