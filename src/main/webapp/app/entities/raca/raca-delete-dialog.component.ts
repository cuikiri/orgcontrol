import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRaca } from 'app/shared/model/raca.model';
import { RacaService } from './raca.service';

@Component({
    selector: 'jhi-raca-delete-dialog',
    templateUrl: './raca-delete-dialog.component.html'
})
export class RacaDeleteDialogComponent {
    raca: IRaca;

    constructor(private racaService: RacaService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.racaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'racaListModification',
                content: 'Deleted an raca'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-raca-delete-popup',
    template: ''
})
export class RacaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ raca }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RacaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.raca = raca;
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
