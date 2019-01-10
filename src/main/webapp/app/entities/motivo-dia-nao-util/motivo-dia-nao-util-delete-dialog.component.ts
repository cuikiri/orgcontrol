import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMotivoDiaNaoUtil } from 'app/shared/model/motivo-dia-nao-util.model';
import { MotivoDiaNaoUtilService } from './motivo-dia-nao-util.service';

@Component({
    selector: 'jhi-motivo-dia-nao-util-delete-dialog',
    templateUrl: './motivo-dia-nao-util-delete-dialog.component.html'
})
export class MotivoDiaNaoUtilDeleteDialogComponent {
    motivoDiaNaoUtil: IMotivoDiaNaoUtil;

    constructor(
        private motivoDiaNaoUtilService: MotivoDiaNaoUtilService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.motivoDiaNaoUtilService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'motivoDiaNaoUtilListModification',
                content: 'Deleted an motivoDiaNaoUtil'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-motivo-dia-nao-util-delete-popup',
    template: ''
})
export class MotivoDiaNaoUtilDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ motivoDiaNaoUtil }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MotivoDiaNaoUtilDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.motivoDiaNaoUtil = motivoDiaNaoUtil;
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
