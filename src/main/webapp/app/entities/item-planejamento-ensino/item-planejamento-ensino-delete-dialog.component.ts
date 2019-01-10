import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IItemPlanejamentoEnsino } from 'app/shared/model/item-planejamento-ensino.model';
import { ItemPlanejamentoEnsinoService } from './item-planejamento-ensino.service';

@Component({
    selector: 'jhi-item-planejamento-ensino-delete-dialog',
    templateUrl: './item-planejamento-ensino-delete-dialog.component.html'
})
export class ItemPlanejamentoEnsinoDeleteDialogComponent {
    itemPlanejamentoEnsino: IItemPlanejamentoEnsino;

    constructor(
        private itemPlanejamentoEnsinoService: ItemPlanejamentoEnsinoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemPlanejamentoEnsinoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'itemPlanejamentoEnsinoListModification',
                content: 'Deleted an itemPlanejamentoEnsino'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-planejamento-ensino-delete-popup',
    template: ''
})
export class ItemPlanejamentoEnsinoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemPlanejamentoEnsino }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ItemPlanejamentoEnsinoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.itemPlanejamentoEnsino = itemPlanejamentoEnsino;
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
