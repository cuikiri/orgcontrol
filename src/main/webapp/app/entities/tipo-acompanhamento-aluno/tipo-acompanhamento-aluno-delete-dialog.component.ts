import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoAcompanhamentoAluno } from 'app/shared/model/tipo-acompanhamento-aluno.model';
import { TipoAcompanhamentoAlunoService } from './tipo-acompanhamento-aluno.service';

@Component({
    selector: 'jhi-tipo-acompanhamento-aluno-delete-dialog',
    templateUrl: './tipo-acompanhamento-aluno-delete-dialog.component.html'
})
export class TipoAcompanhamentoAlunoDeleteDialogComponent {
    tipoAcompanhamentoAluno: ITipoAcompanhamentoAluno;

    constructor(
        private tipoAcompanhamentoAlunoService: TipoAcompanhamentoAlunoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoAcompanhamentoAlunoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoAcompanhamentoAlunoListModification',
                content: 'Deleted an tipoAcompanhamentoAluno'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-acompanhamento-aluno-delete-popup',
    template: ''
})
export class TipoAcompanhamentoAlunoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoAcompanhamentoAluno }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoAcompanhamentoAlunoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoAcompanhamentoAluno = tipoAcompanhamentoAluno;
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
