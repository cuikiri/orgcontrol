import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IRespAtivDescritiva } from 'app/shared/model/resp-ativ-descritiva.model';
import { RespAtivDescritivaService } from './resp-ativ-descritiva.service';
import { IItemAcompanhamentoAtividade } from 'app/shared/model/item-acompanhamento-atividade.model';
import { ItemAcompanhamentoAtividadeService } from 'app/entities/item-acompanhamento-atividade';
import { IRespostaAtividade } from 'app/shared/model/resposta-atividade.model';
import { RespostaAtividadeService } from 'app/entities/resposta-atividade';

@Component({
    selector: 'jhi-resp-ativ-descritiva-update',
    templateUrl: './resp-ativ-descritiva-update.component.html'
})
export class RespAtivDescritivaUpdateComponent implements OnInit {
    respAtivDescritiva: IRespAtivDescritiva;
    isSaving: boolean;

    itemacompanhamentoatividades: IItemAcompanhamentoAtividade[];

    respostaatividades: IRespostaAtividade[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private respAtivDescritivaService: RespAtivDescritivaService,
        private itemAcompanhamentoAtividadeService: ItemAcompanhamentoAtividadeService,
        private respostaAtividadeService: RespostaAtividadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ respAtivDescritiva }) => {
            this.respAtivDescritiva = respAtivDescritiva;
        });
        this.itemAcompanhamentoAtividadeService.query().subscribe(
            (res: HttpResponse<IItemAcompanhamentoAtividade[]>) => {
                this.itemacompanhamentoatividades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.respostaAtividadeService.query().subscribe(
            (res: HttpResponse<IRespostaAtividade[]>) => {
                this.respostaatividades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.respAtivDescritiva.id !== undefined) {
            this.subscribeToSaveResponse(this.respAtivDescritivaService.update(this.respAtivDescritiva));
        } else {
            this.subscribeToSaveResponse(this.respAtivDescritivaService.create(this.respAtivDescritiva));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRespAtivDescritiva>>) {
        result.subscribe((res: HttpResponse<IRespAtivDescritiva>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackItemAcompanhamentoAtividadeById(index: number, item: IItemAcompanhamentoAtividade) {
        return item.id;
    }

    trackRespostaAtividadeById(index: number, item: IRespostaAtividade) {
        return item.id;
    }
}
