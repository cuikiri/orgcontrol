import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoColaborador } from 'app/shared/model/tipo-colaborador.model';
import { TipoColaboradorService } from './tipo-colaborador.service';
import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from 'app/entities/colaborador';

@Component({
    selector: 'jhi-tipo-colaborador-update',
    templateUrl: './tipo-colaborador-update.component.html'
})
export class TipoColaboradorUpdateComponent implements OnInit {
    tipoColaborador: ITipoColaborador;
    isSaving: boolean;

    colaboradors: IColaborador[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoColaboradorService: TipoColaboradorService,
        private colaboradorService: ColaboradorService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoColaborador }) => {
            this.tipoColaborador = tipoColaborador;
        });
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
        if (this.tipoColaborador.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoColaboradorService.update(this.tipoColaborador));
        } else {
            this.subscribeToSaveResponse(this.tipoColaboradorService.create(this.tipoColaborador));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoColaborador>>) {
        result.subscribe((res: HttpResponse<ITipoColaborador>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackColaboradorById(index: number, item: IColaborador) {
        return item.id;
    }
}
