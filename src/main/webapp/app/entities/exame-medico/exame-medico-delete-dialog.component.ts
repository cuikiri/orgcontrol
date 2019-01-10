import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExameMedico } from 'app/shared/model/exame-medico.model';
import { ExameMedicoService } from './exame-medico.service';

@Component({
    selector: 'jhi-exame-medico-delete-dialog',
    templateUrl: './exame-medico-delete-dialog.component.html'
})
export class ExameMedicoDeleteDialogComponent {
    exameMedico: IExameMedico;

    constructor(
        private exameMedicoService: ExameMedicoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.exameMedicoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'exameMedicoListModification',
                content: 'Deleted an exameMedico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-exame-medico-delete-popup',
    template: ''
})
export class ExameMedicoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ exameMedico }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ExameMedicoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.exameMedico = exameMedico;
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
