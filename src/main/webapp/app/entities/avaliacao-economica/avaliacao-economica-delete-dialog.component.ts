import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAvaliacaoEconomica } from 'app/shared/model/avaliacao-economica.model';
import { AvaliacaoEconomicaService } from './avaliacao-economica.service';

@Component({
    selector: 'jhi-avaliacao-economica-delete-dialog',
    templateUrl: './avaliacao-economica-delete-dialog.component.html'
})
export class AvaliacaoEconomicaDeleteDialogComponent {
    avaliacaoEconomica: IAvaliacaoEconomica;

    constructor(
        private avaliacaoEconomicaService: AvaliacaoEconomicaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.avaliacaoEconomicaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'avaliacaoEconomicaListModification',
                content: 'Deleted an avaliacaoEconomica'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-avaliacao-economica-delete-popup',
    template: ''
})
export class AvaliacaoEconomicaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ avaliacaoEconomica }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AvaliacaoEconomicaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.avaliacaoEconomica = avaliacaoEconomica;
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
