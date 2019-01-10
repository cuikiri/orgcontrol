import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IRespAvalOptativaEconomica } from 'app/shared/model/resp-aval-optativa-economica.model';
import { RespAvalOptativaEconomicaService } from './resp-aval-optativa-economica.service';
import { IItemAvaliacaoEconomica } from 'app/shared/model/item-avaliacao-economica.model';
import { ItemAvaliacaoEconomicaService } from 'app/entities/item-avaliacao-economica';
import { IRespostaAvaliacaoEconomica } from 'app/shared/model/resposta-avaliacao-economica.model';
import { RespostaAvaliacaoEconomicaService } from 'app/entities/resposta-avaliacao-economica';

@Component({
    selector: 'jhi-resp-aval-optativa-economica-update',
    templateUrl: './resp-aval-optativa-economica-update.component.html'
})
export class RespAvalOptativaEconomicaUpdateComponent implements OnInit {
    respAvalOptativaEconomica: IRespAvalOptativaEconomica;
    isSaving: boolean;

    itemavaliacaoeconomicas: IItemAvaliacaoEconomica[];

    respostaavaliacaoeconomicas: IRespostaAvaliacaoEconomica[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private respAvalOptativaEconomicaService: RespAvalOptativaEconomicaService,
        private itemAvaliacaoEconomicaService: ItemAvaliacaoEconomicaService,
        private respostaAvaliacaoEconomicaService: RespostaAvaliacaoEconomicaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ respAvalOptativaEconomica }) => {
            this.respAvalOptativaEconomica = respAvalOptativaEconomica;
        });
        this.itemAvaliacaoEconomicaService.query().subscribe(
            (res: HttpResponse<IItemAvaliacaoEconomica[]>) => {
                this.itemavaliacaoeconomicas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.respostaAvaliacaoEconomicaService.query().subscribe(
            (res: HttpResponse<IRespostaAvaliacaoEconomica[]>) => {
                this.respostaavaliacaoeconomicas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.respAvalOptativaEconomica.id !== undefined) {
            this.subscribeToSaveResponse(this.respAvalOptativaEconomicaService.update(this.respAvalOptativaEconomica));
        } else {
            this.subscribeToSaveResponse(this.respAvalOptativaEconomicaService.create(this.respAvalOptativaEconomica));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRespAvalOptativaEconomica>>) {
        result.subscribe(
            (res: HttpResponse<IRespAvalOptativaEconomica>) => this.onSaveSuccess(),
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

    trackItemAvaliacaoEconomicaById(index: number, item: IItemAvaliacaoEconomica) {
        return item.id;
    }

    trackRespostaAvaliacaoEconomicaById(index: number, item: IRespostaAvaliacaoEconomica) {
        return item.id;
    }
}
