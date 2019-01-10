import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IItemAcompanhamentoAtividade } from 'app/shared/model/item-acompanhamento-atividade.model';
import { ItemAcompanhamentoAtividadeService } from './item-acompanhamento-atividade.service';

@Component({
    selector: 'jhi-item-acompanhamento-atividade-delete-dialog',
    templateUrl: './item-acompanhamento-atividade-delete-dialog.component.html'
})
export class ItemAcompanhamentoAtividadeDeleteDialogComponent {
    itemAcompanhamentoAtividade: IItemAcompanhamentoAtividade;

    constructor(
        private itemAcompanhamentoAtividadeService: ItemAcompanhamentoAtividadeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemAcompanhamentoAtividadeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'itemAcompanhamentoAtividadeListModification',
                content: 'Deleted an itemAcompanhamentoAtividade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-acompanhamento-atividade-delete-popup',
    template: ''
})
export class ItemAcompanhamentoAtividadeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemAcompanhamentoAtividade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ItemAcompanhamentoAtividadeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.itemAcompanhamentoAtividade = itemAcompanhamentoAtividade;
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
