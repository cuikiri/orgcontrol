import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoDocumento } from 'app/shared/model/tipo-documento.model';
import { TipoDocumentoService } from './tipo-documento.service';

@Component({
    selector: 'jhi-tipo-documento-delete-dialog',
    templateUrl: './tipo-documento-delete-dialog.component.html'
})
export class TipoDocumentoDeleteDialogComponent {
    tipoDocumento: ITipoDocumento;

    constructor(
        private tipoDocumentoService: TipoDocumentoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoDocumentoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoDocumentoListModification',
                content: 'Deleted an tipoDocumento'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-documento-delete-popup',
    template: ''
})
export class TipoDocumentoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoDocumento }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoDocumentoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoDocumento = tipoDocumento;
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
