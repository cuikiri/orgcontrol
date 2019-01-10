import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanejamentoAtividade } from 'app/shared/model/planejamento-atividade.model';
import { PlanejamentoAtividadeService } from './planejamento-atividade.service';

@Component({
    selector: 'jhi-planejamento-atividade-delete-dialog',
    templateUrl: './planejamento-atividade-delete-dialog.component.html'
})
export class PlanejamentoAtividadeDeleteDialogComponent {
    planejamentoAtividade: IPlanejamentoAtividade;

    constructor(
        private planejamentoAtividadeService: PlanejamentoAtividadeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planejamentoAtividadeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planejamentoAtividadeListModification',
                content: 'Deleted an planejamentoAtividade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-planejamento-atividade-delete-popup',
    template: ''
})
export class PlanejamentoAtividadeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planejamentoAtividade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanejamentoAtividadeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planejamentoAtividade = planejamentoAtividade;
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
