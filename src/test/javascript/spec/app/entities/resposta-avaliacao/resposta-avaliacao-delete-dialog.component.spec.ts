/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrgcontrolTestModule } from '../../../test.module';
import { RespostaAvaliacaoDeleteDialogComponent } from 'app/entities/resposta-avaliacao/resposta-avaliacao-delete-dialog.component';
import { RespostaAvaliacaoService } from 'app/entities/resposta-avaliacao/resposta-avaliacao.service';

describe('Component Tests', () => {
    describe('RespostaAvaliacao Management Delete Component', () => {
        let comp: RespostaAvaliacaoDeleteDialogComponent;
        let fixture: ComponentFixture<RespostaAvaliacaoDeleteDialogComponent>;
        let service: RespostaAvaliacaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrgcontrolTestModule],
                declarations: [RespostaAvaliacaoDeleteDialogComponent]
            })
                .overrideTemplate(RespostaAvaliacaoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespostaAvaliacaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespostaAvaliacaoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
