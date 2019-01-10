import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IItemPlanejamentoAtividade } from 'app/shared/model/item-planejamento-atividade.model';
import { ItemPlanejamentoAtividadeService } from './item-planejamento-atividade.service';

@Component({
    selector: 'jhi-item-planejamento-atividade-delete-dialog',
    templateUrl: './item-planejamento-atividade-delete-dialog.component.html'
})
export class ItemPlanejamentoAtividadeDeleteDialogComponent {
    itemPlanejamentoAtividade: IItemPlanejamentoAtividade;

    constructor(
        private itemPlanejamentoAtividadeService: ItemPlanejamentoAtividadeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemPlanejamentoAtividadeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'itemPlanejamentoAtividadeListModification',
                content: 'Deleted an itemPlanejamentoAtividade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-planejamento-atividade-delete-popup',
    template: ''
})
export class ItemPlanejamentoAtividadeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemPlanejamentoAtividade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ItemPlanejamentoAtividadeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.itemPlanejamentoAtividade = itemPlanejamentoAtividade;
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
