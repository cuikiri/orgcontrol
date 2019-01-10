import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBimestreAcompanhamento } from 'app/shared/model/bimestre-acompanhamento.model';
import { BimestreAcompanhamentoService } from './bimestre-acompanhamento.service';

@Component({
    selector: 'jhi-bimestre-acompanhamento-delete-dialog',
    templateUrl: './bimestre-acompanhamento-delete-dialog.component.html'
})
export class BimestreAcompanhamentoDeleteDialogComponent {
    bimestreAcompanhamento: IBimestreAcompanhamento;

    constructor(
        private bimestreAcompanhamentoService: BimestreAcompanhamentoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bimestreAcompanhamentoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'bimestreAcompanhamentoListModification',
                content: 'Deleted an bimestreAcompanhamento'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-bimestre-acompanhamento-delete-popup',
    template: ''
})
export class BimestreAcompanhamentoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bimestreAcompanhamento }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BimestreAcompanhamentoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.bimestreAcompanhamento = bimestreAcompanhamento;
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
