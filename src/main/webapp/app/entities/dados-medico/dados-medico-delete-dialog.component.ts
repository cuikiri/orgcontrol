import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDadosMedico } from 'app/shared/model/dados-medico.model';
import { DadosMedicoService } from './dados-medico.service';

@Component({
    selector: 'jhi-dados-medico-delete-dialog',
    templateUrl: './dados-medico-delete-dialog.component.html'
})
export class DadosMedicoDeleteDialogComponent {
    dadosMedico: IDadosMedico;

    constructor(
        private dadosMedicoService: DadosMedicoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dadosMedicoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dadosMedicoListModification',
                content: 'Deleted an dadosMedico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dados-medico-delete-popup',
    template: ''
})
export class DadosMedicoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dadosMedico }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DadosMedicoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.dadosMedico = dadosMedico;
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
