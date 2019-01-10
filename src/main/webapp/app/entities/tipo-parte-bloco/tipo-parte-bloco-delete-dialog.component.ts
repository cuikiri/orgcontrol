import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoParteBloco } from 'app/shared/model/tipo-parte-bloco.model';
import { TipoParteBlocoService } from './tipo-parte-bloco.service';

@Component({
    selector: 'jhi-tipo-parte-bloco-delete-dialog',
    templateUrl: './tipo-parte-bloco-delete-dialog.component.html'
})
export class TipoParteBlocoDeleteDialogComponent {
    tipoParteBloco: ITipoParteBloco;

    constructor(
        private tipoParteBlocoService: TipoParteBlocoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoParteBlocoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoParteBlocoListModification',
                content: 'Deleted an tipoParteBloco'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-parte-bloco-delete-popup',
    template: ''
})
export class TipoParteBlocoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoParteBloco }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoParteBlocoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoParteBloco = tipoParteBloco;
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
