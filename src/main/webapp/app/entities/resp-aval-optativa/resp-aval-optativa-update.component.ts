import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IRespAvalOptativa } from 'app/shared/model/resp-aval-optativa.model';
import { RespAvalOptativaService } from './resp-aval-optativa.service';
import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
import { ItemAvaliacaoService } from 'app/entities/item-avaliacao';
import { IRespostaAvaliacao } from 'app/shared/model/resposta-avaliacao.model';
import { RespostaAvaliacaoService } from 'app/entities/resposta-avaliacao';

@Component({
    selector: 'jhi-resp-aval-optativa-update',
    templateUrl: './resp-aval-optativa-update.component.html'
})
export class RespAvalOptativaUpdateComponent implements OnInit {
    respAvalOptativa: IRespAvalOptativa;
    isSaving: boolean;

    itemavaliacaos: IItemAvaliacao[];

    respostaavaliacaos: IRespostaAvaliacao[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private respAvalOptativaService: RespAvalOptativaService,
        private itemAvaliacaoService: ItemAvaliacaoService,
        private respostaAvaliacaoService: RespostaAvaliacaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ respAvalOptativa }) => {
            this.respAvalOptativa = respAvalOptativa;
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
        if (this.respAvalOptativa.id !== undefined) {
            this.subscribeToSaveResponse(this.respAvalOptativaService.update(this.respAvalOptativa));
        } else {
            this.subscribeToSaveResponse(this.respAvalOptativaService.create(this.respAvalOptativa));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRespAvalOptativa>>) {
        result.subscribe((res: HttpResponse<IRespAvalOptativa>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
