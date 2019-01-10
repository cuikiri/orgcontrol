import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMateria } from 'app/shared/model/materia.model';
import { MateriaService } from './materia.service';
import { IHorarioMateria } from 'app/shared/model/horario-materia.model';
import { HorarioMateriaService } from 'app/entities/horario-materia';
import { IDiario } from 'app/shared/model/diario.model';
import { DiarioService } from 'app/entities/diario';

@Component({
    selector: 'jhi-materia-update',
    templateUrl: './materia-update.component.html'
})
export class MateriaUpdateComponent implements OnInit {
    materia: IMateria;
    isSaving: boolean;

    horariomaterias: IHorarioMateria[];

    diarios: IDiario[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private materiaService: MateriaService,
        private horarioMateriaService: HorarioMateriaService,
        private diarioService: DiarioService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ materia }) => {
            this.materia = materia;
        });
        this.horarioMateriaService.query().subscribe(
            (res: HttpResponse<IHorarioMateria[]>) => {
                this.horariomaterias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.diarioService.query().subscribe(
            (res: HttpResponse<IDiario[]>) => {
                this.diarios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.materia.id !== undefined) {
            this.subscribeToSaveResponse(this.materiaService.update(this.materia));
        } else {
            this.subscribeToSaveResponse(this.materiaService.create(this.materia));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMateria>>) {
        result.subscribe((res: HttpResponse<IMateria>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackHorarioMateriaById(index: number, item: IHorarioMateria) {
        return item.id;
    }

    trackDiarioById(index: number, item: IDiario) {
        return item.id;
    }
}
