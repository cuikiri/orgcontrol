import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBiotipo } from 'app/shared/model/biotipo.model';
import { BiotipoService } from './biotipo.service';

@Component({
    selector: 'jhi-biotipo-delete-dialog',
    templateUrl: './biotipo-delete-dialog.component.html'
})
export class BiotipoDeleteDialogComponent {
    biotipo: IBiotipo;

    constructor(private biotipoService: BiotipoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.biotipoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'biotipoListModification',
                content: 'Deleted an biotipo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-biotipo-delete-popup',
    template: ''
})
export class BiotipoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ biotipo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BiotipoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.biotipo = biotipo;
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
