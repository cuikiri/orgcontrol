import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IRespAtivOptativa } from 'app/shared/model/resp-ativ-optativa.model';
import { RespAtivOptativaService } from './resp-ativ-optativa.service';
import { IItemAcompanhamentoAtividade } from 'app/shared/model/item-acompanhamento-atividade.model';
import { ItemAcompanhamentoAtividadeService } from 'app/entities/item-acompanhamento-atividade';
import { IRespostaAtividade } from 'app/shared/model/resposta-atividade.model';
import { RespostaAtividadeService } from 'app/entities/resposta-atividade';

@Component({
    selector: 'jhi-resp-ativ-optativa-update',
    templateUrl: './resp-ativ-optativa-update.component.html'
})
export class RespAtivOptativaUpdateComponent implements OnInit {
    respAtivOptativa: IRespAtivOptativa;
    isSaving: boolean;

    itemacompanhamentoatividades: IItemAcompanhamentoAtividade[];

    respostaatividades: IRespostaAtividade[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private respAtivOptativaService: RespAtivOptativaService,
        private itemAcompanhamentoAtividadeService: ItemAcompanhamentoAtividadeService,
        private respostaAtividadeService: RespostaAtividadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ respAtivOptativa }) => {
            this.respAtivOptativa = respAtivOptativa;
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
        if (this.respAtivOptativa.id !== undefined) {
            this.subscribeToSaveResponse(this.respAtivOptativaService.update(this.respAtivOptativa));
        } else {
            this.subscribeToSaveResponse(this.respAtivOptativaService.create(this.respAtivOptativa));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRespAtivOptativa>>) {
        result.subscribe((res: HttpResponse<IRespAtivOptativa>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
