/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DiarioDetailComponent } from 'app/entities/diario/diario-detail.component';
import { Diario } from 'app/shared/model/diario.model';

describe('Component Tests', () => {
    describe('Diario Management Detail Component', () => {
        let comp: DiarioDetailComponent;
        let fixture: ComponentFixture<DiarioDetailComponent>;
        const route = ({ data: of({ diario: new Diario(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DiarioDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DiarioDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DiarioDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.diario).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
