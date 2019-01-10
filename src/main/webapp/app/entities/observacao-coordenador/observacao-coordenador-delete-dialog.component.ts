import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IObservacaoCoordenador } from 'app/shared/model/observacao-coordenador.model';
import { ObservacaoCoordenadorService } from './observacao-coordenador.service';

@Component({
    selector: 'jhi-observacao-coordenador-delete-dialog',
    templateUrl: './observacao-coordenador-delete-dialog.component.html'
})
export class ObservacaoCoordenadorDeleteDialogComponent {
    observacaoCoordenador: IObservacaoCoordenador;

    constructor(
        private observacaoCoordenadorService: ObservacaoCoordenadorService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.observacaoCoordenadorService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'observacaoCoordenadorListModification',
                content: 'Deleted an observacaoCoordenador'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-observacao-coordenador-delete-popup',
    template: ''
})
export class ObservacaoCoordenadorDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ observacaoCoordenador }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ObservacaoCoordenadorDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.observacaoCoordenador = observacaoCoordenador;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
