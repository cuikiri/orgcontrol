import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParteBloco } from 'app/shared/model/parte-bloco.model';
import { ParteBlocoService } from './parte-bloco.service';

@Component({
    selector: 'jhi-parte-bloco-delete-dialog',
    templateUrl: './parte-bloco-delete-dialog.component.html'
})
export class ParteBlocoDeleteDialogComponent {
    parteBloco: IParteBloco;

    constructor(private parteBlocoService: ParteBlocoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.parteBlocoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'parteBlocoListModification',
                content: 'Deleted an parteBloco'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-parte-bloco-delete-popup',
    template: ''
})
export class ParteBlocoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ parteBloco }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ParteBlocoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.parteBloco = parteBloco;
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
