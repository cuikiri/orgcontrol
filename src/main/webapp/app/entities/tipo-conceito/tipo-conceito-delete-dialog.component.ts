import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoConceito } from 'app/shared/model/tipo-conceito.model';
import { TipoConceitoService } from './tipo-conceito.service';

@Component({
    selector: 'jhi-tipo-conceito-delete-dialog',
    templateUrl: './tipo-conceito-delete-dialog.component.html'
})
export class TipoConceitoDeleteDialogComponent {
    tipoConceito: ITipoConceito;

    constructor(
        private tipoConceitoService: TipoConceitoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoConceitoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoConceitoListModification',
                content: 'Deleted an tipoConceito'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-conceito-delete-popup',
    template: ''
})
export class TipoConceitoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoConceito }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoConceitoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoConceito = tipoConceito;
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
