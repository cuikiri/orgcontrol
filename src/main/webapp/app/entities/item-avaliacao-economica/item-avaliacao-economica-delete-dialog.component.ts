import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IItemAvaliacaoEconomica } from 'app/shared/model/item-avaliacao-economica.model';
import { ItemAvaliacaoEconomicaService } from './item-avaliacao-economica.service';

@Component({
    selector: 'jhi-item-avaliacao-economica-delete-dialog',
    templateUrl: './item-avaliacao-economica-delete-dialog.component.html'
})
export class ItemAvaliacaoEconomicaDeleteDialogComponent {
    itemAvaliacaoEconomica: IItemAvaliacaoEconomica;

    constructor(
        private itemAvaliacaoEconomicaService: ItemAvaliacaoEconomicaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemAvaliacaoEconomicaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'itemAvaliacaoEconomicaListModification',
                content: 'Deleted an itemAvaliacaoEconomica'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-avaliacao-economica-delete-popup',
    template: ''
})
export class ItemAvaliacaoEconomicaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemAvaliacaoEconomica }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ItemAvaliacaoEconomicaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.itemAvaliacaoEconomica = itemAvaliacaoEconomica;
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
