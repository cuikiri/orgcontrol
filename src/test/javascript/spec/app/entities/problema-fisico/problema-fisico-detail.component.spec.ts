/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { ProblemaFisicoDetailComponent } from 'app/entities/problema-fisico/problema-fisico-detail.component';
import { ProblemaFisico } from 'app/shared/model/problema-fisico.model';

describe('Component Tests', () => {
    describe('ProblemaFisico Management Detail Component', () => {
        let comp: ProblemaFisicoDetailComponent;
        let fixture: ComponentFixture<ProblemaFisicoDetailComponent>;
        const route = ({ data: of({ problemaFisico: new ProblemaFisico(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [ProblemaFisicoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProblemaFisicoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProblemaFisicoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.problemaFisico).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
