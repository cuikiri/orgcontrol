import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRespAvalDescritiva } from 'app/shared/model/resp-aval-descritiva.model';
import { RespAvalDescritivaService } from './resp-aval-descritiva.service';

@Component({
    selector: 'jhi-resp-aval-descritiva-delete-dialog',
    templateUrl: './resp-aval-descritiva-delete-dialog.component.html'
})
export class RespAvalDescritivaDeleteDialogComponent {
    respAvalDescritiva: IRespAvalDescritiva;

    constructor(
        private respAvalDescritivaService: RespAvalDescritivaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.respAvalDescritivaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'respAvalDescritivaListModification',
                content: 'Deleted an respAvalDescritiva'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resp-aval-descritiva-delete-popup',
    template: ''
})
export class RespAvalDescritivaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAvalDescritiva }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RespAvalDescritivaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.respAvalDescritiva = respAvalDescritiva;
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
