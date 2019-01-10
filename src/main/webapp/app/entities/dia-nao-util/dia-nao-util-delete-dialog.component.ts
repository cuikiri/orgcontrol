import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDiaNaoUtil } from 'app/shared/model/dia-nao-util.model';
import { DiaNaoUtilService } from './dia-nao-util.service';

@Component({
    selector: 'jhi-dia-nao-util-delete-dialog',
    templateUrl: './dia-nao-util-delete-dialog.component.html'
})
export class DiaNaoUtilDeleteDialogComponent {
    diaNaoUtil: IDiaNaoUtil;

    constructor(private diaNaoUtilService: DiaNaoUtilService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.diaNaoUtilService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'diaNaoUtilListModification',
                content: 'Deleted an diaNaoUtil'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dia-nao-util-delete-popup',
    template: ''
})
export class DiaNaoUtilDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ diaNaoUtil }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DiaNaoUtilDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.diaNaoUtil = diaNaoUtil;
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
