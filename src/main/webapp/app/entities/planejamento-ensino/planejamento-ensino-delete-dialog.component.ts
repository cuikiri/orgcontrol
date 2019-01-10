import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanejamentoEnsino } from 'app/shared/model/planejamento-ensino.model';
import { PlanejamentoEnsinoService } from './planejamento-ensino.service';

@Component({
    selector: 'jhi-planejamento-ensino-delete-dialog',
    templateUrl: './planejamento-ensino-delete-dialog.component.html'
})
export class PlanejamentoEnsinoDeleteDialogComponent {
    planejamentoEnsino: IPlanejamentoEnsino;

    constructor(
        private planejamentoEnsinoService: PlanejamentoEnsinoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planejamentoEnsinoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planejamentoEnsinoListModification',
                content: 'Deleted an planejamentoEnsino'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-planejamento-ensino-delete-popup',
    template: ''
})
export class PlanejamentoEnsinoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planejamentoEnsino }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanejamentoEnsinoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planejamentoEnsino = planejamentoEnsino;
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
