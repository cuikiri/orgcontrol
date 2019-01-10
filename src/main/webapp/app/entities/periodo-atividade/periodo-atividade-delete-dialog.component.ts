import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPeriodoAtividade } from 'app/shared/model/periodo-atividade.model';
import { PeriodoAtividadeService } from './periodo-atividade.service';

@Component({
    selector: 'jhi-periodo-atividade-delete-dialog',
    templateUrl: './periodo-atividade-delete-dialog.component.html'
})
export class PeriodoAtividadeDeleteDialogComponent {
    periodoAtividade: IPeriodoAtividade;

    constructor(
        private periodoAtividadeService: PeriodoAtividadeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.periodoAtividadeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'periodoAtividadeListModification',
                content: 'Deleted an periodoAtividade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-periodo-atividade-delete-popup',
    template: ''
})
export class PeriodoAtividadeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ periodoAtividade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PeriodoAtividadeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.periodoAtividade = periodoAtividade;
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
