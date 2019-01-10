import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRespAvalDescritivaEconomica } from 'app/shared/model/resp-aval-descritiva-economica.model';
import { RespAvalDescritivaEconomicaService } from './resp-aval-descritiva-economica.service';

@Component({
    selector: 'jhi-resp-aval-descritiva-economica-delete-dialog',
    templateUrl: './resp-aval-descritiva-economica-delete-dialog.component.html'
})
export class RespAvalDescritivaEconomicaDeleteDialogComponent {
    respAvalDescritivaEconomica: IRespAvalDescritivaEconomica;

    constructor(
        private respAvalDescritivaEconomicaService: RespAvalDescritivaEconomicaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.respAvalDescritivaEconomicaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'respAvalDescritivaEconomicaListModification',
                content: 'Deleted an respAvalDescritivaEconomica'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resp-aval-descritiva-economica-delete-popup',
    template: ''
})
export class RespAvalDescritivaEconomicaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAvalDescritivaEconomica }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RespAvalDescritivaEconomicaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.respAvalDescritivaEconomica = respAvalDescritivaEconomica;
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
