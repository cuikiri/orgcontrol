/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { VacinaDetailComponent } from 'app/entities/vacina/vacina-detail.component';
import { Vacina } from 'app/shared/model/vacina.model';

describe('Component Tests', () => {
    describe('Vacina Management Detail Component', () => {
        let comp: VacinaDetailComponent;
        let fixture: ComponentFixture<VacinaDetailComponent>;
        const route = ({ data: of({ vacina: new Vacina(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [VacinaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VacinaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VacinaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.vacina).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
