import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDadoBiologico } from 'app/shared/model/dado-biologico.model';
import { DadoBiologicoService } from './dado-biologico.service';

@Component({
    selector: 'jhi-dado-biologico-delete-dialog',
    templateUrl: './dado-biologico-delete-dialog.component.html'
})
export class DadoBiologicoDeleteDialogComponent {
    dadoBiologico: IDadoBiologico;

    constructor(
        private dadoBiologicoService: DadoBiologicoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dadoBiologicoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dadoBiologicoListModification',
                content: 'Deleted an dadoBiologico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dado-biologico-delete-popup',
    template: ''
})
export class DadoBiologicoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dadoBiologico }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DadoBiologicoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.dadoBiologico = dadoBiologico;
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
