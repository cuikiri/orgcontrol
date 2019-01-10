import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPeriodoSemana } from 'app/shared/model/periodo-semana.model';
import { PeriodoSemanaService } from './periodo-semana.service';

@Component({
    selector: 'jhi-periodo-semana-delete-dialog',
    templateUrl: './periodo-semana-delete-dialog.component.html'
})
export class PeriodoSemanaDeleteDialogComponent {
    periodoSemana: IPeriodoSemana;

    constructor(
        private periodoSemanaService: PeriodoSemanaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.periodoSemanaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'periodoSemanaListModification',
                content: 'Deleted an periodoSemana'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-periodo-semana-delete-popup',
    template: ''
})
export class PeriodoSemanaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ periodoSemana }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PeriodoSemanaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.periodoSemana = periodoSemana;
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
