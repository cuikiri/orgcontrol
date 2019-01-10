import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDependenteLegal } from 'app/shared/model/dependente-legal.model';
import { DependenteLegalService } from './dependente-legal.service';

@Component({
    selector: 'jhi-dependente-legal-delete-dialog',
    templateUrl: './dependente-legal-delete-dialog.component.html'
})
export class DependenteLegalDeleteDialogComponent {
    dependenteLegal: IDependenteLegal;

    constructor(
        private dependenteLegalService: DependenteLegalService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dependenteLegalService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dependenteLegalListModification',
                content: 'Deleted an dependenteLegal'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dependente-legal-delete-popup',
    template: ''
})
export class DependenteLegalDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dependenteLegal }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DependenteLegalDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.dependenteLegal = dependenteLegal;
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
