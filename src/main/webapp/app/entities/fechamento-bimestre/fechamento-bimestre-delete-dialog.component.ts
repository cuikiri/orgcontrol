import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';
import { FechamentoBimestreService } from './fechamento-bimestre.service';

@Component({
    selector: 'jhi-fechamento-bimestre-delete-dialog',
    templateUrl: './fechamento-bimestre-delete-dialog.component.html'
})
export class FechamentoBimestreDeleteDialogComponent {
    fechamentoBimestre: IFechamentoBimestre;

    constructor(
        private fechamentoBimestreService: FechamentoBimestreService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fechamentoBimestreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'fechamentoBimestreListModification',
                content: 'Deleted an fechamentoBimestre'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-fechamento-bimestre-delete-popup',
    template: ''
})
export class FechamentoBimestreDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fechamentoBimestre }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FechamentoBimestreDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.fechamentoBimestre = fechamentoBimestre;
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
