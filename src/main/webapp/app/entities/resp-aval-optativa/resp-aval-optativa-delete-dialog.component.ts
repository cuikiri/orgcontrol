import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRespAvalOptativa } from 'app/shared/model/resp-aval-optativa.model';
import { RespAvalOptativaService } from './resp-aval-optativa.service';

@Component({
    selector: 'jhi-resp-aval-optativa-delete-dialog',
    templateUrl: './resp-aval-optativa-delete-dialog.component.html'
})
export class RespAvalOptativaDeleteDialogComponent {
    respAvalOptativa: IRespAvalOptativa;

    constructor(
        private respAvalOptativaService: RespAvalOptativaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.respAvalOptativaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'respAvalOptativaListModification',
                content: 'Deleted an respAvalOptativa'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resp-aval-optativa-delete-popup',
    template: ''
})
export class RespAvalOptativaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAvalOptativa }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RespAvalOptativaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.respAvalOptativa = respAvalOptativa;
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
