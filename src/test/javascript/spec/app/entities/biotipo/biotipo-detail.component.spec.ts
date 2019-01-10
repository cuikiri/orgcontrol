/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { BiotipoDetailComponent } from 'app/entities/biotipo/biotipo-detail.component';
import { Biotipo } from 'app/shared/model/biotipo.model';

describe('Component Tests', () => {
    describe('Biotipo Management Detail Component', () => {
        let comp: BiotipoDetailComponent;
        let fixture: ComponentFixture<BiotipoDetailComponent>;
        const route = ({ data: of({ biotipo: new Biotipo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [BiotipoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BiotipoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BiotipoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.biotipo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
