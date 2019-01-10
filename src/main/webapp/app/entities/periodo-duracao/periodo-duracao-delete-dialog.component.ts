import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPeriodoDuracao } from 'app/shared/model/periodo-duracao.model';
import { PeriodoDuracaoService } from './periodo-duracao.service';

@Component({
    selector: 'jhi-periodo-duracao-delete-dialog',
    templateUrl: './periodo-duracao-delete-dialog.component.html'
})
export class PeriodoDuracaoDeleteDialogComponent {
    periodoDuracao: IPeriodoDuracao;

    constructor(
        private periodoDuracaoService: PeriodoDuracaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.periodoDuracaoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'periodoDuracaoListModification',
                content: 'Deleted an periodoDuracao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-periodo-duracao-delete-popup',
    template: ''
})
export class PeriodoDuracaoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ periodoDuracao }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PeriodoDuracaoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.periodoDuracao = periodoDuracao;
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
