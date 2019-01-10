import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAnotacao } from 'app/shared/model/anotacao.model';
import { AnotacaoService } from './anotacao.service';

@Component({
    selector: 'jhi-anotacao-delete-dialog',
    templateUrl: './anotacao-delete-dialog.component.html'
})
export class AnotacaoDeleteDialogComponent {
    anotacao: IAnotacao;

    constructor(private anotacaoService: AnotacaoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.anotacaoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'anotacaoListModification',
                content: 'Deleted an anotacao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-anotacao-delete-popup',
    template: ''
})
export class AnotacaoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ anotacao }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AnotacaoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.anotacao = anotacao;
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
