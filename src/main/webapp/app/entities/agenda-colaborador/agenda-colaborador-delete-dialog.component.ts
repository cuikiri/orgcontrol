import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAgendaColaborador } from 'app/shared/model/agenda-colaborador.model';
import { AgendaColaboradorService } from './agenda-colaborador.service';

@Component({
    selector: 'jhi-agenda-colaborador-delete-dialog',
    templateUrl: './agenda-colaborador-delete-dialog.component.html'
})
export class AgendaColaboradorDeleteDialogComponent {
    agendaColaborador: IAgendaColaborador;

    constructor(
        private agendaColaboradorService: AgendaColaboradorService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.agendaColaboradorService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'agendaColaboradorListModification',
                content: 'Deleted an agendaColaborador'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-agenda-colaborador-delete-popup',
    template: ''
})
export class AgendaColaboradorDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ agendaColaborador }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AgendaColaboradorDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.agendaColaborador = agendaColaborador;
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
