import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoBiotipo } from 'app/shared/model/tipo-biotipo.model';
import { TipoBiotipoService } from './tipo-biotipo.service';

@Component({
    selector: 'jhi-tipo-biotipo-delete-dialog',
    templateUrl: './tipo-biotipo-delete-dialog.component.html'
})
export class TipoBiotipoDeleteDialogComponent {
    tipoBiotipo: ITipoBiotipo;

    constructor(
        private tipoBiotipoService: TipoBiotipoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoBiotipoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoBiotipoListModification',
                content: 'Deleted an tipoBiotipo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-biotipo-delete-popup',
    template: ''
})
export class TipoBiotipoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoBiotipo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoBiotipoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoBiotipo = tipoBiotipo;
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
