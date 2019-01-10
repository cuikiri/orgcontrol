import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProblemaFisico } from 'app/shared/model/problema-fisico.model';
import { ProblemaFisicoService } from './problema-fisico.service';

@Component({
    selector: 'jhi-problema-fisico-delete-dialog',
    templateUrl: './problema-fisico-delete-dialog.component.html'
})
export class ProblemaFisicoDeleteDialogComponent {
    problemaFisico: IProblemaFisico;

    constructor(
        private problemaFisicoService: ProblemaFisicoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.problemaFisicoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'problemaFisicoListModification',
                content: 'Deleted an problemaFisico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-problema-fisico-delete-popup',
    template: ''
})
export class ProblemaFisicoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ problemaFisico }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProblemaFisicoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.problemaFisico = problemaFisico;
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
