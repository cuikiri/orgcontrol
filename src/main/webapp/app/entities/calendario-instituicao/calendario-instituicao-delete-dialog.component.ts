import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';
import { CalendarioInstituicaoService } from './calendario-instituicao.service';

@Component({
    selector: 'jhi-calendario-instituicao-delete-dialog',
    templateUrl: './calendario-instituicao-delete-dialog.component.html'
})
export class CalendarioInstituicaoDeleteDialogComponent {
    calendarioInstituicao: ICalendarioInstituicao;

    constructor(
        private calendarioInstituicaoService: CalendarioInstituicaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.calendarioInstituicaoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'calendarioInstituicaoListModification',
                content: 'Deleted an calendarioInstituicao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-calendario-instituicao-delete-popup',
    template: ''
})
export class CalendarioInstituicaoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ calendarioInstituicao }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CalendarioInstituicaoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.calendarioInstituicao = calendarioInstituicao;
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
