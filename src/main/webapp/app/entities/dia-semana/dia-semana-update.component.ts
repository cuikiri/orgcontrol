import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDiaSemana } from 'app/shared/model/dia-semana.model';
import { DiaSemanaService } from './dia-semana.service';
import { IHorarioMateria } from 'app/shared/model/horario-materia.model';
import { HorarioMateriaService } from 'app/entities/horario-materia';
import { IPeriodoSemana } from 'app/shared/model/periodo-semana.model';
import { PeriodoSemanaService } from 'app/entities/periodo-semana';

@Component({
    selector: 'jhi-dia-semana-update',
    templateUrl: './dia-semana-update.component.html'
})
export class DiaSemanaUpdateComponent implements OnInit {
    diaSemana: IDiaSemana;
    isSaving: boolean;

    horariomaterias: IHorarioMateria[];

    periodosemanas: IPeriodoSemana[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private diaSemanaService: DiaSemanaService,
        private horarioMateriaService: HorarioMateriaService,
        private periodoSemanaService: PeriodoSemanaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ diaSemana }) => {
            this.diaSemana = diaSemana;
        });
        this.horarioMateriaService.query().subscribe(
            (res: HttpResponse<IHorarioMateria[]>) => {
                this.horariomaterias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.periodoSemanaService.query().subscribe(
            (res: HttpResponse<IPeriodoSemana[]>) => {
                this.periodosemanas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.diaSemana.id !== undefined) {
            this.subscribeToSaveResponse(this.diaSemanaService.update(this.diaSemana));
        } else {
            this.subscribeToSaveResponse(this.diaSemanaService.create(this.diaSemana));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDiaSemana>>) {
        result.subscribe((res: HttpResponse<IDiaSemana>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPeriodoSemanaById(index: number, item: IPeriodoSemana) {
        return item.id;
    }
}
