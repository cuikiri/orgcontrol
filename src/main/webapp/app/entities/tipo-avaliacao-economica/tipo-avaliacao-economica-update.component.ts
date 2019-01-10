import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoAvaliacaoEconomica } from 'app/shared/model/tipo-avaliacao-economica.model';
import { TipoAvaliacaoEconomicaService } from './tipo-avaliacao-economica.service';
import { IAvaliacaoEconomica } from 'app/shared/model/avaliacao-economica.model';
import { AvaliacaoEconomicaService } from 'app/entities/avaliacao-economica';

@Component({
    selector: 'jhi-tipo-avaliacao-economica-update',
    templateUrl: './tipo-avaliacao-economica-update.component.html'
})
export class TipoAvaliacaoEconomicaUpdateComponent implements OnInit {
    tipoAvaliacaoEconomica: ITipoAvaliacaoEconomica;
    isSaving: boolean;

    avaliacaoeconomicas: IAvaliacaoEconomica[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoAvaliacaoEconomicaService: TipoAvaliacaoEconomicaService,
        private avaliacaoEconomicaService: AvaliacaoEconomicaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoAvaliacaoEconomica }) => {
            this.tipoAvaliacaoEconomica = tipoAvaliacaoEconomica;
        });
        this.avaliacaoEconomicaService.query().subscribe(
            (res: HttpResponse<IAvaliacaoEconomica[]>) => {
                this.avaliacaoeconomicas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipoAvaliacaoEconomica.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoAvaliacaoEconomicaService.update(this.tipoAvaliacaoEconomica));
        } else {
            this.subscribeToSaveResponse(this.tipoAvaliacaoEconomicaService.create(this.tipoAvaliacaoEconomica));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoAvaliacaoEconomica>>) {
        result.subscribe(
            (res: HttpResponse<ITipoAvaliacaoEconomica>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackAvaliacaoEconomicaById(index: number, item: IAvaliacaoEconomica) {
        return item.id;
    }
}
