import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRespAvalOptativaEconomica } from 'app/shared/model/resp-aval-optativa-economica.model';
import { RespAvalOptativaEconomicaService } from './resp-aval-optativa-economica.service';

@Component({
    selector: 'jhi-resp-aval-optativa-economica-delete-dialog',
    templateUrl: './resp-aval-optativa-economica-delete-dialog.component.html'
})
export class RespAvalOptativaEconomicaDeleteDialogComponent {
    respAvalOptativaEconomica: IRespAvalOptativaEconomica;

    constructor(
        private respAvalOptativaEconomicaService: RespAvalOptativaEconomicaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.respAvalOptativaEconomicaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'respAvalOptativaEconomicaListModification',
                content: 'Deleted an respAvalOptativaEconomica'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resp-aval-optativa-economica-delete-popup',
    template: ''
})
export class RespAvalOptativaEconomicaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAvalOptativaEconomica }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RespAvalOptativaEconomicaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.respAvalOptativaEconomica = respAvalOptativaEconomica;
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
