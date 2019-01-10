import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAgendaColaborador } from 'app/shared/model/agenda-colaborador.model';
import { AgendaColaboradorService } from './agenda-colaborador.service';
import { IDiaSemana } from 'app/shared/model/dia-semana.model';
import { DiaSemanaService } from 'app/entities/dia-semana';
import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from 'app/entities/colaborador';

@Component({
    selector: 'jhi-agenda-colaborador-update',
    templateUrl: './agenda-colaborador-update.component.html'
})
export class AgendaColaboradorUpdateComponent implements OnInit {
    agendaColaborador: IAgendaColaborador;
    isSaving: boolean;

    diasemanas: IDiaSemana[];

    colaboradors: IColaborador[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private agendaColaboradorService: AgendaColaboradorService,
        private diaSemanaService: DiaSemanaService,
        private colaboradorService: ColaboradorService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ agendaColaborador }) => {
            this.agendaColaborador = agendaColaborador;
        });
        this.diaSemanaService.query().subscribe(
            (res: HttpResponse<IDiaSemana[]>) => {
                this.diasemanas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.colaboradorService.query().subscribe(
            (res: HttpResponse<IColaborador[]>) => {
                this.colaboradors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.agendaColaborador.id !== undefined) {
            this.subscribeToSaveResponse(this.agendaColaboradorService.update(this.agendaColaborador));
        } else {
            this.subscribeToSaveResponse(this.agendaColaboradorService.create(this.agendaColaborador));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAgendaColaborador>>) {
        result.subscribe((res: HttpResponse<IAgendaColaborador>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDiaSemanaById(index: number, item: IDiaSemana) {
        return item.id;
    }

    trackColaboradorById(index: number, item: IColaborador) {
        return item.id;
    }
}
