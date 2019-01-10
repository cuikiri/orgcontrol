import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFotoDocumento } from 'app/shared/model/foto-documento.model';
import { FotoDocumentoService } from './foto-documento.service';

@Component({
    selector: 'jhi-foto-documento-delete-dialog',
    templateUrl: './foto-documento-delete-dialog.component.html'
})
export class FotoDocumentoDeleteDialogComponent {
    fotoDocumento: IFotoDocumento;

    constructor(
        private fotoDocumentoService: FotoDocumentoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fotoDocumentoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'fotoDocumentoListModification',
                content: 'Deleted an fotoDocumento'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-foto-documento-delete-popup',
    template: ''
})
export class FotoDocumentoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fotoDocumento }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FotoDocumentoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.fotoDocumento = fotoDocumento;
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
