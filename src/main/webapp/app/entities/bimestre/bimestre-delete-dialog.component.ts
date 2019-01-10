import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBimestre } from 'app/shared/model/bimestre.model';
import { BimestreService } from './bimestre.service';

@Component({
    selector: 'jhi-bimestre-delete-dialog',
    templateUrl: './bimestre-delete-dialog.component.html'
})
export class BimestreDeleteDialogComponent {
    bimestre: IBimestre;

    constructor(private bimestreService: BimestreService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bimestreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'bimestreListModification',
                content: 'Deleted an bimestre'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-bimestre-delete-popup',
    template: ''
})
export class BimestreDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bimestre }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BimestreDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.bimestre = bimestre;
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
