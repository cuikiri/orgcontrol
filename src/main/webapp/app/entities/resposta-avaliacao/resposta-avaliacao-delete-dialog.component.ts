import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRespostaAvaliacao } from 'app/shared/model/resposta-avaliacao.model';
import { RespostaAvaliacaoService } from './resposta-avaliacao.service';

@Component({
    selector: 'jhi-resposta-avaliacao-delete-dialog',
    templateUrl: './resposta-avaliacao-delete-dialog.component.html'
})
export class RespostaAvaliacaoDeleteDialogComponent {
    respostaAvaliacao: IRespostaAvaliacao;

    constructor(
        private respostaAvaliacaoService: RespostaAvaliacaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.respostaAvaliacaoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'respostaAvaliacaoListModification',
                content: 'Deleted an respostaAvaliacao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resposta-avaliacao-delete-popup',
    template: ''
})
export class RespostaAvaliacaoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respostaAvaliacao }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RespostaAvaliacaoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.respostaAvaliacao = respostaAvaliacao;
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
