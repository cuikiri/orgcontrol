import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMateriaAcompanhamento } from 'app/shared/model/materia-acompanhamento.model';
import { MateriaAcompanhamentoService } from './materia-acompanhamento.service';

@Component({
    selector: 'jhi-materia-acompanhamento-delete-dialog',
    templateUrl: './materia-acompanhamento-delete-dialog.component.html'
})
export class MateriaAcompanhamentoDeleteDialogComponent {
    materiaAcompanhamento: IMateriaAcompanhamento;

    constructor(
        private materiaAcompanhamentoService: MateriaAcompanhamentoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.materiaAcompanhamentoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'materiaAcompanhamentoListModification',
                content: 'Deleted an materiaAcompanhamento'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-materia-acompanhamento-delete-popup',
    template: ''
})
export class MateriaAcompanhamentoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ materiaAcompanhamento }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MateriaAcompanhamentoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.materiaAcompanhamento = materiaAcompanhamento;
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
