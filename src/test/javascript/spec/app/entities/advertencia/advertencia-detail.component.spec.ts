/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { AdvertenciaDetailComponent } from 'app/entities/advertencia/advertencia-detail.component';
import { Advertencia } from 'app/shared/model/advertencia.model';

describe('Component Tests', () => {
    describe('Advertencia Management Detail Component', () => {
        let comp: AdvertenciaDetailComponent;
        let fixture: ComponentFixture<AdvertenciaDetailComponent>;
        const route = ({ data: of({ advertencia: new Advertencia(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [AdvertenciaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AdvertenciaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdvertenciaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.advertencia).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
