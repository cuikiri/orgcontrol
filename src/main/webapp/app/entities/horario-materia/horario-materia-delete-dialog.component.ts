import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHorarioMateria } from 'app/shared/model/horario-materia.model';
import { HorarioMateriaService } from './horario-materia.service';

@Component({
    selector: 'jhi-horario-materia-delete-dialog',
    templateUrl: './horario-materia-delete-dialog.component.html'
})
export class HorarioMateriaDeleteDialogComponent {
    horarioMateria: IHorarioMateria;

    constructor(
        private horarioMateriaService: HorarioMateriaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.horarioMateriaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'horarioMateriaListModification',
                content: 'Deleted an horarioMateria'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-horario-materia-delete-popup',
    template: ''
})
export class HorarioMateriaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ horarioMateria }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HorarioMateriaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.horarioMateria = horarioMateria;
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
