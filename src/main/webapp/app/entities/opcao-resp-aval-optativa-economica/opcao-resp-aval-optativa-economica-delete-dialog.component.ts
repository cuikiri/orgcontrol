import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOpcaoRespAvalOptativaEconomica } from 'app/shared/model/opcao-resp-aval-optativa-economica.model';
import { OpcaoRespAvalOptativaEconomicaService } from './opcao-resp-aval-optativa-economica.service';

@Component({
    selector: 'jhi-opcao-resp-aval-optativa-economica-delete-dialog',
    templateUrl: './opcao-resp-aval-optativa-economica-delete-dialog.component.html'
})
export class OpcaoRespAvalOptativaEconomicaDeleteDialogComponent {
    opcaoRespAvalOptativaEconomica: IOpcaoRespAvalOptativaEconomica;

    constructor(
        private opcaoRespAvalOptativaEconomicaService: OpcaoRespAvalOptativaEconomicaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.opcaoRespAvalOptativaEconomicaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'opcaoRespAvalOptativaEconomicaListModification',
                content: 'Deleted an opcaoRespAvalOptativaEconomica'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-opcao-resp-aval-optativa-economica-delete-popup',
    template: ''
})
export class OpcaoRespAvalOptativaEconomicaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ opcaoRespAvalOptativaEconomica }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OpcaoRespAvalOptativaEconomicaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.opcaoRespAvalOptativaEconomica = opcaoRespAvalOptativaEconomica;
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
