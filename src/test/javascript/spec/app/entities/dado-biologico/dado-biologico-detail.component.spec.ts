/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { DadoBiologicoDetailComponent } from 'app/entities/dado-biologico/dado-biologico-detail.component';
import { DadoBiologico } from 'app/shared/model/dado-biologico.model';

describe('Component Tests', () => {
    describe('DadoBiologico Management Detail Component', () => {
        let comp: DadoBiologicoDetailComponent;
        let fixture: ComponentFixture<DadoBiologicoDetailComponent>;
        const route = ({ data: of({ dadoBiologico: new DadoBiologico(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [DadoBiologicoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DadoBiologicoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DadoBiologicoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.dadoBiologico).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
