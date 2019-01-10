import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoAvaliacaoEconomica } from 'app/shared/model/tipo-avaliacao-economica.model';
import { TipoAvaliacaoEconomicaService } from './tipo-avaliacao-economica.service';

@Component({
    selector: 'jhi-tipo-avaliacao-economica-delete-dialog',
    templateUrl: './tipo-avaliacao-economica-delete-dialog.component.html'
})
export class TipoAvaliacaoEconomicaDeleteDialogComponent {
    tipoAvaliacaoEconomica: ITipoAvaliacaoEconomica;

    constructor(
        private tipoAvaliacaoEconomicaService: TipoAvaliacaoEconomicaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoAvaliacaoEconomicaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoAvaliacaoEconomicaListModification',
                content: 'Deleted an tipoAvaliacaoEconomica'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-avaliacao-economica-delete-popup',
    template: ''
})
export class TipoAvaliacaoEconomicaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoAvaliacaoEconomica }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoAvaliacaoEconomicaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoAvaliacaoEconomica = tipoAvaliacaoEconomica;
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
