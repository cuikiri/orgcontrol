import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRespAtivOptativa } from 'app/shared/model/resp-ativ-optativa.model';
import { RespAtivOptativaService } from './resp-ativ-optativa.service';

@Component({
    selector: 'jhi-resp-ativ-optativa-delete-dialog',
    templateUrl: './resp-ativ-optativa-delete-dialog.component.html'
})
export class RespAtivOptativaDeleteDialogComponent {
    respAtivOptativa: IRespAtivOptativa;

    constructor(
        private respAtivOptativaService: RespAtivOptativaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.respAtivOptativaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'respAtivOptativaListModification',
                content: 'Deleted an respAtivOptativa'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resp-ativ-optativa-delete-popup',
    template: ''
})
export class RespAtivOptativaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAtivOptativa }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RespAtivOptativaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.respAtivOptativa = respAtivOptativa;
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
