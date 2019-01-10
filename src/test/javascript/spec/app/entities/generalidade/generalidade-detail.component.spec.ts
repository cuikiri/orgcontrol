/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { GeneralidadeDetailComponent } from 'app/entities/generalidade/generalidade-detail.component';
import { Generalidade } from 'app/shared/model/generalidade.model';

describe('Component Tests', () => {
    describe('Generalidade Management Detail Component', () => {
        let comp: GeneralidadeDetailComponent;
        let fixture: ComponentFixture<GeneralidadeDetailComponent>;
        const route = ({ data: of({ generalidade: new Generalidade(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [GeneralidadeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GeneralidadeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GeneralidadeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.generalidade).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
