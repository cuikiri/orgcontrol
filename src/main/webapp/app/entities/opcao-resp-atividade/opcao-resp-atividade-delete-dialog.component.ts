import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOpcaoRespAtividade } from 'app/shared/model/opcao-resp-atividade.model';
import { OpcaoRespAtividadeService } from './opcao-resp-atividade.service';

@Component({
    selector: 'jhi-opcao-resp-atividade-delete-dialog',
    templateUrl: './opcao-resp-atividade-delete-dialog.component.html'
})
export class OpcaoRespAtividadeDeleteDialogComponent {
    opcaoRespAtividade: IOpcaoRespAtividade;

    constructor(
        private opcaoRespAtividadeService: OpcaoRespAtividadeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.opcaoRespAtividadeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'opcaoRespAtividadeListModification',
                content: 'Deleted an opcaoRespAtividade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-opcao-resp-atividade-delete-popup',
    template: ''
})
export class OpcaoRespAtividadeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ opcaoRespAtividade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OpcaoRespAtividadeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.opcaoRespAtividade = opcaoRespAtividade;
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
