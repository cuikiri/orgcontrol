import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeneralidade } from 'app/shared/model/generalidade.model';
import { GeneralidadeService } from './generalidade.service';

@Component({
    selector: 'jhi-generalidade-delete-dialog',
    templateUrl: './generalidade-delete-dialog.component.html'
})
export class GeneralidadeDeleteDialogComponent {
    generalidade: IGeneralidade;

    constructor(
        private generalidadeService: GeneralidadeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.generalidadeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'generalidadeListModification',
                content: 'Deleted an generalidade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-generalidade-delete-popup',
    template: ''
})
export class GeneralidadeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ generalidade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GeneralidadeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.generalidade = generalidade;
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
