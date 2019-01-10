import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUf } from 'app/shared/model/uf.model';
import { UfService } from './uf.service';

@Component({
    selector: 'jhi-uf-delete-dialog',
    templateUrl: './uf-delete-dialog.component.html'
})
export class UfDeleteDialogComponent {
    uf: IUf;

    constructor(private ufService: UfService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ufService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'ufListModification',
                content: 'Deleted an uf'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-uf-delete-popup',
    template: ''
})
export class UfDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ uf }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UfDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.uf = uf;
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
