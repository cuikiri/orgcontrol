/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { BlocoDetailComponent } from 'app/entities/bloco/bloco-detail.component';
import { Bloco } from 'app/shared/model/bloco.model';

describe('Component Tests', () => {
    describe('Bloco Management Detail Component', () => {
        let comp: BlocoDetailComponent;
        let fixture: ComponentFixture<BlocoDetailComponent>;
        const route = ({ data: of({ bloco: new Bloco(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [BlocoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BlocoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BlocoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.bloco).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
