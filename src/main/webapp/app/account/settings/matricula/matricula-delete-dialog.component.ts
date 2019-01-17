import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMatricula } from 'app/shared/model/matricula.model';
import { MatriculaService } from './matricula.service';

@Component({
    selector: 'jhi-matricula-delete-dialog',
    templateUrl: './matricula-delete-dialog.component.html'
})
export class MatriculaDeleteDialogComponent {
    matricula: IMatricula;

    constructor(private matriculaService: MatriculaService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.matriculaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'matriculaListModification',
                content: 'Deleted an matricula'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-matricula-delete-popup',
    template: ''
})
export class MatriculaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ matricula }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MatriculaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.matricula = matricula;
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
