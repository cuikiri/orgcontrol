import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdvertencia } from 'app/shared/model/advertencia.model';
import { AdvertenciaService } from './advertencia.service';

@Component({
    selector: 'jhi-advertencia-delete-dialog',
    templateUrl: './advertencia-delete-dialog.component.html'
})
export class AdvertenciaDeleteDialogComponent {
    advertencia: IAdvertencia;

    constructor(
        private advertenciaService: AdvertenciaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.advertenciaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'advertenciaListModification',
                content: 'Deleted an advertencia'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-advertencia-delete-popup',
    template: ''
})
export class AdvertenciaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ advertencia }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AdvertenciaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.advertencia = advertencia;
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
