import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
import { ItemAvaliacaoService } from './item-avaliacao.service';

@Component({
    selector: 'jhi-item-avaliacao-delete-dialog',
    templateUrl: './item-avaliacao-delete-dialog.component.html'
})
export class ItemAvaliacaoDeleteDialogComponent {
    itemAvaliacao: IItemAvaliacao;

    constructor(
        private itemAvaliacaoService: ItemAvaliacaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemAvaliacaoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'itemAvaliacaoListModification',
                content: 'Deleted an itemAvaliacao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-avaliacao-delete-popup',
    template: ''
})
export class ItemAvaliacaoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemAvaliacao }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ItemAvaliacaoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.itemAvaliacao = itemAvaliacao;
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
