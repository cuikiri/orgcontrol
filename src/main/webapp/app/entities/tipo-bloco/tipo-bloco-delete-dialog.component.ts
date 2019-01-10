import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoBloco } from 'app/shared/model/tipo-bloco.model';
import { TipoBlocoService } from './tipo-bloco.service';

@Component({
    selector: 'jhi-tipo-bloco-delete-dialog',
    templateUrl: './tipo-bloco-delete-dialog.component.html'
})
export class TipoBlocoDeleteDialogComponent {
    tipoBloco: ITipoBloco;

    constructor(private tipoBlocoService: TipoBlocoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoBlocoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoBlocoListModification',
                content: 'Deleted an tipoBloco'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-bloco-delete-popup',
    template: ''
})
export class TipoBlocoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoBloco }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoBlocoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.tipoBloco = tipoBloco;
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
