import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IItemPlanejamentoInstituicao } from 'app/shared/model/item-planejamento-instituicao.model';
import { ItemPlanejamentoInstituicaoService } from './item-planejamento-instituicao.service';

@Component({
    selector: 'jhi-item-planejamento-instituicao-delete-dialog',
    templateUrl: './item-planejamento-instituicao-delete-dialog.component.html'
})
export class ItemPlanejamentoInstituicaoDeleteDialogComponent {
    itemPlanejamentoInstituicao: IItemPlanejamentoInstituicao;

    constructor(
        private itemPlanejamentoInstituicaoService: ItemPlanejamentoInstituicaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemPlanejamentoInstituicaoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'itemPlanejamentoInstituicaoListModification',
                content: 'Deleted an itemPlanejamentoInstituicao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-planejamento-instituicao-delete-popup',
    template: ''
})
export class ItemPlanejamentoInstituicaoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemPlanejamentoInstituicao }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ItemPlanejamentoInstituicaoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.itemPlanejamentoInstituicao = itemPlanejamentoInstituicao;
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
