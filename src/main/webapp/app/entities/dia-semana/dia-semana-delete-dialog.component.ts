import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDiaSemana } from 'app/shared/model/dia-semana.model';
import { DiaSemanaService } from './dia-semana.service';

@Component({
    selector: 'jhi-dia-semana-delete-dialog',
    templateUrl: './dia-semana-delete-dialog.component.html'
})
export class DiaSemanaDeleteDialogComponent {
    diaSemana: IDiaSemana;

    constructor(private diaSemanaService: DiaSemanaService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.diaSemanaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'diaSemanaListModification',
                content: 'Deleted an diaSemana'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dia-semana-delete-popup',
    template: ''
})
export class DiaSemanaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ diaSemana }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DiaSemanaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.diaSemana = diaSemana;
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
