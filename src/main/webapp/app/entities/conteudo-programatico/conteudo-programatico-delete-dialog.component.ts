import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConteudoProgramatico } from 'app/shared/model/conteudo-programatico.model';
import { ConteudoProgramaticoService } from './conteudo-programatico.service';

@Component({
    selector: 'jhi-conteudo-programatico-delete-dialog',
    templateUrl: './conteudo-programatico-delete-dialog.component.html'
})
export class ConteudoProgramaticoDeleteDialogComponent {
    conteudoProgramatico: IConteudoProgramatico;

    constructor(
        private conteudoProgramaticoService: ConteudoProgramaticoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.conteudoProgramaticoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'conteudoProgramaticoListModification',
                content: 'Deleted an conteudoProgramatico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-conteudo-programatico-delete-popup',
    template: ''
})
export class ConteudoProgramaticoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conteudoProgramatico }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConteudoProgramaticoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.conteudoProgramatico = conteudoProgramatico;
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
