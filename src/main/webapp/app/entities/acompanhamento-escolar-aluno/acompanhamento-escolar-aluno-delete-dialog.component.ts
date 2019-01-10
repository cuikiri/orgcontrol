import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAcompanhamentoEscolarAluno } from 'app/shared/model/acompanhamento-escolar-aluno.model';
import { AcompanhamentoEscolarAlunoService } from './acompanhamento-escolar-aluno.service';

@Component({
    selector: 'jhi-acompanhamento-escolar-aluno-delete-dialog',
    templateUrl: './acompanhamento-escolar-aluno-delete-dialog.component.html'
})
export class AcompanhamentoEscolarAlunoDeleteDialogComponent {
    acompanhamentoEscolarAluno: IAcompanhamentoEscolarAluno;

    constructor(
        private acompanhamentoEscolarAlunoService: AcompanhamentoEscolarAlunoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.acompanhamentoEscolarAlunoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'acompanhamentoEscolarAlunoListModification',
                content: 'Deleted an acompanhamentoEscolarAluno'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-acompanhamento-escolar-aluno-delete-popup',
    template: ''
})
export class AcompanhamentoEscolarAlunoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ acompanhamentoEscolarAluno }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AcompanhamentoEscolarAlunoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.acompanhamentoEscolarAluno = acompanhamentoEscolarAluno;
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
