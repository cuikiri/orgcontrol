import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoadoBiologico } from 'app/shared/model/tipoado-biologico.model';
import { TipoadoBiologicoService } from './tipoado-biologico.service';

@Component({
    selector: 'jhi-tipoado-biologico-delete-dialog',
    templateUrl: './tipoado-biologico-delete-dialog.component.html'
})
export class TipoadoBiologicoDeleteDialogComponent {
    tipoadoBiologico: ITipoadoBiologico;

    constructor(
        private tipoadoBiologicoService: TipoadoBiologicoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoadoBiologicoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoadoBiologicoListModification',
                content: 'Deleted an tipoadoBiologico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipoado-biologico-delete-popup',
    template: ''
})
export class TipoadoBiologicoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoadoBiologico }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoadoBiologicoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoadoBiologico = tipoadoBiologico;
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
