/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrgcontrolTestModule } from '../../../test.module';
import { TipoParteBlocoDetailComponent } from 'app/entities/tipo-parte-bloco/tipo-parte-bloco-detail.component';
import { TipoParteBloco } from 'app/shared/model/tipo-parte-bloco.model';

describe('Component Tests', () => {
    describe('TipoParteBloco Management Detail Component', () => {
        let comp: TipoParteBlocoDetailComponent;
        let fixture: ComponentFixture<TipoParteBlocoDetailComponent>;
        const route = ({ data: of({ tipoParteBloco: new TipoParteBloco(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [TipoParteBlocoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoParteBlocoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoParteBlocoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoParteBloco).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
