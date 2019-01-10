import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRespostaAtividade } from 'app/shared/model/resposta-atividade.model';
import { RespostaAtividadeService } from './resposta-atividade.service';

@Component({
    selector: 'jhi-resposta-atividade-delete-dialog',
    templateUrl: './resposta-atividade-delete-dialog.component.html'
})
export class RespostaAtividadeDeleteDialogComponent {
    respostaAtividade: IRespostaAtividade;

    constructor(
        private respostaAtividadeService: RespostaAtividadeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.respostaAtividadeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'respostaAtividadeListModification',
                content: 'Deleted an respostaAtividade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resposta-atividade-delete-popup',
    template: ''
})
export class RespostaAtividadeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respostaAtividade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RespostaAtividadeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.respostaAtividade = respostaAtividade;
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
