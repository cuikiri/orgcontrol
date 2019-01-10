import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoContratacao } from 'app/shared/model/tipo-contratacao.model';
import { TipoContratacaoService } from './tipo-contratacao.service';

@Component({
    selector: 'jhi-tipo-contratacao-delete-dialog',
    templateUrl: './tipo-contratacao-delete-dialog.component.html'
})
export class TipoContratacaoDeleteDialogComponent {
    tipoContratacao: ITipoContratacao;

    constructor(
        private tipoContratacaoService: TipoContratacaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoContratacaoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoContratacaoListModification',
                content: 'Deleted an tipoContratacao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-contratacao-delete-popup',
    template: ''
})
export class TipoContratacaoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoContratacao }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoContratacaoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoContratacao = tipoContratacao;
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
