import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILocomocao } from 'app/shared/model/locomocao.model';
import { LocomocaoService } from './locomocao.service';

@Component({
    selector: 'jhi-locomocao-delete-dialog',
    templateUrl: './locomocao-delete-dialog.component.html'
})
export class LocomocaoDeleteDialogComponent {
    locomocao: ILocomocao;

    constructor(private locomocaoService: LocomocaoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.locomocaoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'locomocaoListModification',
                content: 'Deleted an locomocao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-locomocao-delete-popup',
    template: ''
})
export class LocomocaoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ locomocao }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LocomocaoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.locomocao = locomocao;
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
