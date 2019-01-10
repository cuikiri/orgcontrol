import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoAtividade } from 'app/shared/model/tipo-atividade.model';
import { TipoAtividadeService } from './tipo-atividade.service';

@Component({
    selector: 'jhi-tipo-atividade-delete-dialog',
    templateUrl: './tipo-atividade-delete-dialog.component.html'
})
export class TipoAtividadeDeleteDialogComponent {
    tipoAtividade: ITipoAtividade;

    constructor(
        private tipoAtividadeService: TipoAtividadeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoAtividadeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoAtividadeListModification',
                content: 'Deleted an tipoAtividade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-atividade-delete-popup',
    template: ''
})
export class TipoAtividadeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoAtividade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoAtividadeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoAtividade = tipoAtividade;
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
