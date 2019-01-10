import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFotoAcompanhamentoAluno } from 'app/shared/model/foto-acompanhamento-aluno.model';
import { FotoAcompanhamentoAlunoService } from './foto-acompanhamento-aluno.service';

@Component({
    selector: 'jhi-foto-acompanhamento-aluno-delete-dialog',
    templateUrl: './foto-acompanhamento-aluno-delete-dialog.component.html'
})
export class FotoAcompanhamentoAlunoDeleteDialogComponent {
    fotoAcompanhamentoAluno: IFotoAcompanhamentoAluno;

    constructor(
        private fotoAcompanhamentoAlunoService: FotoAcompanhamentoAlunoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fotoAcompanhamentoAlunoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'fotoAcompanhamentoAlunoListModification',
                content: 'Deleted an fotoAcompanhamentoAluno'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-foto-acompanhamento-aluno-delete-popup',
    template: ''
})
export class FotoAcompanhamentoAlunoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fotoAcompanhamentoAluno }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FotoAcompanhamentoAlunoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.fotoAcompanhamentoAluno = fotoAcompanhamentoAluno;
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
