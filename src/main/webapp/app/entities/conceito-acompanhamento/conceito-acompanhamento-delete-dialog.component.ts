import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConceitoAcompanhamento } from 'app/shared/model/conceito-acompanhamento.model';
import { ConceitoAcompanhamentoService } from './conceito-acompanhamento.service';

@Component({
    selector: 'jhi-conceito-acompanhamento-delete-dialog',
    templateUrl: './conceito-acompanhamento-delete-dialog.component.html'
})
export class ConceitoAcompanhamentoDeleteDialogComponent {
    conceitoAcompanhamento: IConceitoAcompanhamento;

    constructor(
        private conceitoAcompanhamentoService: ConceitoAcompanhamentoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.conceitoAcompanhamentoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'conceitoAcompanhamentoListModification',
                content: 'Deleted an conceitoAcompanhamento'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-conceito-acompanhamento-delete-popup',
    template: ''
})
export class ConceitoAcompanhamentoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conceitoAcompanhamento }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConceitoAcompanhamentoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.conceitoAcompanhamento = conceitoAcompanhamento;
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
