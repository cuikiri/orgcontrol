import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRespostaAvaliacaoEconomica } from 'app/shared/model/resposta-avaliacao-economica.model';
import { RespostaAvaliacaoEconomicaService } from './resposta-avaliacao-economica.service';

@Component({
    selector: 'jhi-resposta-avaliacao-economica-delete-dialog',
    templateUrl: './resposta-avaliacao-economica-delete-dialog.component.html'
})
export class RespostaAvaliacaoEconomicaDeleteDialogComponent {
    respostaAvaliacaoEconomica: IRespostaAvaliacaoEconomica;

    constructor(
        private respostaAvaliacaoEconomicaService: RespostaAvaliacaoEconomicaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.respostaAvaliacaoEconomicaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'respostaAvaliacaoEconomicaListModification',
                content: 'Deleted an respostaAvaliacaoEconomica'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resposta-avaliacao-economica-delete-popup',
    template: ''
})
export class RespostaAvaliacaoEconomicaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respostaAvaliacaoEconomica }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RespostaAvaliacaoEconomicaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.respostaAvaliacaoEconomica = respostaAvaliacaoEconomica;
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
