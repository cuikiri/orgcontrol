import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanejamentoInstituicao } from 'app/shared/model/planejamento-instituicao.model';
import { PlanejamentoInstituicaoService } from './planejamento-instituicao.service';

@Component({
    selector: 'jhi-planejamento-instituicao-delete-dialog',
    templateUrl: './planejamento-instituicao-delete-dialog.component.html'
})
export class PlanejamentoInstituicaoDeleteDialogComponent {
    planejamentoInstituicao: IPlanejamentoInstituicao;

    constructor(
        private planejamentoInstituicaoService: PlanejamentoInstituicaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planejamentoInstituicaoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planejamentoInstituicaoListModification',
                content: 'Deleted an planejamentoInstituicao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-planejamento-instituicao-delete-popup',
    template: ''
})
export class PlanejamentoInstituicaoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planejamentoInstituicao }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanejamentoInstituicaoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planejamentoInstituicao = planejamentoInstituicao;
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
