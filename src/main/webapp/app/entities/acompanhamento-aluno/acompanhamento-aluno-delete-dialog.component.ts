import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAcompanhamentoAluno } from 'app/shared/model/acompanhamento-aluno.model';
import { AcompanhamentoAlunoService } from './acompanhamento-aluno.service';

@Component({
    selector: 'jhi-acompanhamento-aluno-delete-dialog',
    templateUrl: './acompanhamento-aluno-delete-dialog.component.html'
})
export class AcompanhamentoAlunoDeleteDialogComponent {
    acompanhamentoAluno: IAcompanhamentoAluno;

    constructor(
        private acompanhamentoAlunoService: AcompanhamentoAlunoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.acompanhamentoAlunoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'acompanhamentoAlunoListModification',
                content: 'Deleted an acompanhamentoAluno'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-acompanhamento-aluno-delete-popup',
    template: ''
})
export class AcompanhamentoAlunoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ acompanhamentoAluno }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AcompanhamentoAlunoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.acompanhamentoAluno = acompanhamentoAluno;
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
