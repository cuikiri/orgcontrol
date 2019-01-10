import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IRespAvalDescritiva } from 'app/shared/model/resp-aval-descritiva.model';
import { RespAvalDescritivaService } from './resp-aval-descritiva.service';
import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
import { ItemAvaliacaoService } from 'app/entities/item-avaliacao';
import { IRespostaAvaliacao } from 'app/shared/model/resposta-avaliacao.model';
import { RespostaAvaliacaoService } from 'app/entities/resposta-avaliacao';

@Component({
    selector: 'jhi-resp-aval-descritiva-update',
    templateUrl: './resp-aval-descritiva-update.component.html'
})
export class RespAvalDescritivaUpdateComponent implements OnInit {
    respAvalDescritiva: IRespAvalDescritiva;
    isSaving: boolean;

    itemavaliacaos: IItemAvaliacao[];

    respostaavaliacaos: IRespostaAvaliacao[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private respAvalDescritivaService: RespAvalDescritivaService,
        private itemAvaliacaoService: ItemAvaliacaoService,
        private respostaAvaliacaoService: RespostaAvaliacaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ respAvalDescritiva }) => {
            this.respAvalDescritiva = respAvalDescritiva;
        });
        this.itemAvaliacaoService.query().subscribe(
            (res: HttpResponse<IItemAvaliacao[]>) => {
                this.itemavaliacaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.respostaAvaliacaoService.query().subscribe(
            (res: HttpResponse<IRespostaAvaliacao[]>) => {
                this.respostaavaliacaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.respAvalDescritiva.id !== undefined) {
            this.subscribeToSaveResponse(this.respAvalDescritivaService.update(this.respAvalDescritiva));
        } else {
            this.subscribeToSaveResponse(this.respAvalDescritivaService.create(this.respAvalDescritiva));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRespAvalDescritiva>>) {
        result.subscribe((res: HttpResponse<IRespAvalDescritiva>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackItemAvaliacaoById(index: number, item: IItemAvaliacao) {
        return item.id;
    }

    trackRespostaAvaliacaoById(index: number, item: IRespostaAvaliacao) {
        return item.id;
    }
}
