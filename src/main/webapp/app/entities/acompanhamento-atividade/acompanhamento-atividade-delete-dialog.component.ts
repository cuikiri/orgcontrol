import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAcompanhamentoAtividade } from 'app/shared/model/acompanhamento-atividade.model';
import { AcompanhamentoAtividadeService } from './acompanhamento-atividade.service';

@Component({
    selector: 'jhi-acompanhamento-atividade-delete-dialog',
    templateUrl: './acompanhamento-atividade-delete-dialog.component.html'
})
export class AcompanhamentoAtividadeDeleteDialogComponent {
    acompanhamentoAtividade: IAcompanhamentoAtividade;

    constructor(
        private acompanhamentoAtividadeService: AcompanhamentoAtividadeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.acompanhamentoAtividadeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'acompanhamentoAtividadeListModification',
                content: 'Deleted an acompanhamentoAtividade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-acompanhamento-atividade-delete-popup',
    template: ''
})
export class AcompanhamentoAtividadeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ acompanhamentoAtividade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AcompanhamentoAtividadeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.acompanhamentoAtividade = acompanhamentoAtividade;
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
