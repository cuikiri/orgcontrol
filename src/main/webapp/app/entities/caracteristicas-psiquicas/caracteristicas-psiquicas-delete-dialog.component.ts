import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICaracteristicasPsiquicas } from 'app/shared/model/caracteristicas-psiquicas.model';
import { CaracteristicasPsiquicasService } from './caracteristicas-psiquicas.service';

@Component({
    selector: 'jhi-caracteristicas-psiquicas-delete-dialog',
    templateUrl: './caracteristicas-psiquicas-delete-dialog.component.html'
})
export class CaracteristicasPsiquicasDeleteDialogComponent {
    caracteristicasPsiquicas: ICaracteristicasPsiquicas;

    constructor(
        private caracteristicasPsiquicasService: CaracteristicasPsiquicasService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.caracteristicasPsiquicasService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'caracteristicasPsiquicasListModification',
                content: 'Deleted an caracteristicasPsiquicas'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-caracteristicas-psiquicas-delete-popup',
    template: ''
})
export class CaracteristicasPsiquicasDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ caracteristicasPsiquicas }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CaracteristicasPsiquicasDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.caracteristicasPsiquicas = caracteristicasPsiquicas;
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
