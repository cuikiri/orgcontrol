import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoAcompanhamentoAtividade } from 'app/shared/model/tipo-acompanhamento-atividade.model';
import { TipoAcompanhamentoAtividadeService } from './tipo-acompanhamento-atividade.service';

@Component({
    selector: 'jhi-tipo-acompanhamento-atividade-delete-dialog',
    templateUrl: './tipo-acompanhamento-atividade-delete-dialog.component.html'
})
export class TipoAcompanhamentoAtividadeDeleteDialogComponent {
    tipoAcompanhamentoAtividade: ITipoAcompanhamentoAtividade;

    constructor(
        private tipoAcompanhamentoAtividadeService: TipoAcompanhamentoAtividadeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoAcompanhamentoAtividadeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoAcompanhamentoAtividadeListModification',
                content: 'Deleted an tipoAcompanhamentoAtividade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-acompanhamento-atividade-delete-popup',
    template: ''
})
export class TipoAcompanhamentoAtividadeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoAcompanhamentoAtividade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoAcompanhamentoAtividadeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoAcompanhamentoAtividade = tipoAcompanhamentoAtividade;
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
