/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ParteBlocoDetailComponent } from 'app/entities/parte-bloco/parte-bloco-detail.component';
import { ParteBloco } from 'app/shared/model/parte-bloco.model';

describe('Component Tests', () => {
    describe('ParteBloco Management Detail Component', () => {
        let comp: ParteBlocoDetailComponent;
        let fixture: ComponentFixture<ParteBlocoDetailComponent>;
        const route = ({ data: of({ parteBloco: new ParteBloco(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ParteBlocoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ParteBlocoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParteBlocoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.parteBloco).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
