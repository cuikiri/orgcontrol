import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConceito } from 'app/shared/model/conceito.model';
import { ConceitoService } from './conceito.service';

@Component({
    selector: 'jhi-conceito-delete-dialog',
    templateUrl: './conceito-delete-dialog.component.html'
})
export class ConceitoDeleteDialogComponent {
    conceito: IConceito;

    constructor(private conceitoService: ConceitoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.conceitoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'conceitoListModification',
                content: 'Deleted an conceito'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-conceito-delete-popup',
    template: ''
})
export class ConceitoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conceito }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConceitoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.conceito = conceito;
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
