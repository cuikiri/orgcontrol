import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPeriodoSemana } from 'app/shared/model/periodo-semana.model';
import { PeriodoSemanaService } from './periodo-semana.service';

@Component({
    selector: 'jhi-periodo-semana-update',
    templateUrl: './periodo-semana-update.component.html'
})
export class PeriodoSemanaUpdateComponent implements OnInit {
    periodoSemana: IPeriodoSemana;
    isSaving: boolean;

    constructor(private periodoSemanaService: PeriodoSemanaService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ periodoSemana }) => {
            this.periodoSemana = periodoSemana;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.periodoSemana.id !== undefined) {
            this.subscribeToSaveResponse(this.periodoSemanaService.update(this.periodoSemana));
        } else {
            this.subscribeToSaveResponse(this.periodoSemanaService.create(this.periodoSemana));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPeriodoSemana>>) {
        result.subscribe((res: HttpResponse<IPeriodoSemana>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
