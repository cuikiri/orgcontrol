import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoAvaliacao } from 'app/shared/model/tipo-avaliacao.model';
import { TipoAvaliacaoService } from './tipo-avaliacao.service';

@Component({
    selector: 'jhi-tipo-avaliacao-delete-dialog',
    templateUrl: './tipo-avaliacao-delete-dialog.component.html'
})
export class TipoAvaliacaoDeleteDialogComponent {
    tipoAvaliacao: ITipoAvaliacao;

    constructor(
        private tipoAvaliacaoService: TipoAvaliacaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoAvaliacaoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoAvaliacaoListModification',
                content: 'Deleted an tipoAvaliacao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-avaliacao-delete-popup',
    template: ''
})
export class TipoAvaliacaoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoAvaliacao }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoAvaliacaoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoAvaliacao = tipoAvaliacao;
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
