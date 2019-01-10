import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoColaborador } from 'app/shared/model/tipo-colaborador.model';
import { TipoColaboradorService } from './tipo-colaborador.service';

@Component({
    selector: 'jhi-tipo-colaborador-delete-dialog',
    templateUrl: './tipo-colaborador-delete-dialog.component.html'
})
export class TipoColaboradorDeleteDialogComponent {
    tipoColaborador: ITipoColaborador;

    constructor(
        private tipoColaboradorService: TipoColaboradorService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoColaboradorService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoColaboradorListModification',
                content: 'Deleted an tipoColaborador'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-colaborador-delete-popup',
    template: ''
})
export class TipoColaboradorDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoColaborador }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoColaboradorDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoColaborador = tipoColaborador;
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
