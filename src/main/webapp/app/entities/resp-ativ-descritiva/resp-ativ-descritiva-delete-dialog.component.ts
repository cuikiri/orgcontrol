import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRespAtivDescritiva } from 'app/shared/model/resp-ativ-descritiva.model';
import { RespAtivDescritivaService } from './resp-ativ-descritiva.service';

@Component({
    selector: 'jhi-resp-ativ-descritiva-delete-dialog',
    templateUrl: './resp-ativ-descritiva-delete-dialog.component.html'
})
export class RespAtivDescritivaDeleteDialogComponent {
    respAtivDescritiva: IRespAtivDescritiva;

    constructor(
        private respAtivDescritivaService: RespAtivDescritivaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.respAtivDescritivaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'respAtivDescritivaListModification',
                content: 'Deleted an respAtivDescritiva'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resp-ativ-descritiva-delete-popup',
    template: ''
})
export class RespAtivDescritivaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAtivDescritiva }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RespAtivDescritivaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.respAtivDescritiva = respAtivDescritiva;
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
