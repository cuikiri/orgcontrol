import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoUnidade } from 'app/shared/model/tipo-unidade.model';
import { TipoUnidadeService } from './tipo-unidade.service';

@Component({
    selector: 'jhi-tipo-unidade-delete-dialog',
    templateUrl: './tipo-unidade-delete-dialog.component.html'
})
export class TipoUnidadeDeleteDialogComponent {
    tipoUnidade: ITipoUnidade;

    constructor(
        private tipoUnidadeService: TipoUnidadeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoUnidadeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoUnidadeListModification',
                content: 'Deleted an tipoUnidade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-unidade-delete-popup',
    template: ''
})
export class TipoUnidadeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoUnidade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoUnidadeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoUnidade = tipoUnidade;
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
