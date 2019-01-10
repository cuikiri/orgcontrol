import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOpcaoRespAvalOptativa } from 'app/shared/model/opcao-resp-aval-optativa.model';
import { OpcaoRespAvalOptativaService } from './opcao-resp-aval-optativa.service';

@Component({
    selector: 'jhi-opcao-resp-aval-optativa-delete-dialog',
    templateUrl: './opcao-resp-aval-optativa-delete-dialog.component.html'
})
export class OpcaoRespAvalOptativaDeleteDialogComponent {
    opcaoRespAvalOptativa: IOpcaoRespAvalOptativa;

    constructor(
        private opcaoRespAvalOptativaService: OpcaoRespAvalOptativaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.opcaoRespAvalOptativaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'opcaoRespAvalOptativaListModification',
                content: 'Deleted an opcaoRespAvalOptativa'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-opcao-resp-aval-optativa-delete-popup',
    template: ''
})
export class OpcaoRespAvalOptativaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ opcaoRespAvalOptativa }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OpcaoRespAvalOptativaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.opcaoRespAvalOptativa = opcaoRespAvalOptativa;
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
