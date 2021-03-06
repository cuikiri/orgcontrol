import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResponsavel } from 'app/shared/model/responsavel.model';
import { ResponsavelService } from './responsavel.service';

@Component({
    selector: 'jhi-responsavel-delete-dialog',
    templateUrl: './responsavel-delete-dialog.component.html'
})
export class ResponsavelDeleteDialogComponent {
    responsavel: IResponsavel;

    constructor(
        private responsavelService: ResponsavelService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.responsavelService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'responsavelListModification',
                content: 'Deleted an responsavel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-responsavel-delete-popup',
    template: ''
})
export class ResponsavelDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ responsavel }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ResponsavelDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.responsavel = responsavel;
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
