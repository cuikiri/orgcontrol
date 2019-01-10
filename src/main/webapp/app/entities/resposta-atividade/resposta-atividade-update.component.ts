import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IRespostaAtividade } from 'app/shared/model/resposta-atividade.model';
import { RespostaAtividadeService } from './resposta-atividade.service';
import { IRespAtivDescritiva } from 'app/shared/model/resp-ativ-descritiva.model';
import { RespAtivDescritivaService } from 'app/entities/resp-ativ-descritiva';
import { IRespAtivOptativa } from 'app/shared/model/resp-ativ-optativa.model';
import { RespAtivOptativaService } from 'app/entities/resp-ativ-optativa';
import { IConceito } from 'app/shared/model/conceito.model';
import { ConceitoService } from 'app/entities/conceito';
import { IItemAcompanhamentoAtividade } from 'app/shared/model/item-acompanhamento-atividade.model';
import { ItemAcompanhamentoAtividadeService } from 'app/entities/item-acompanhamento-atividade';

@Component({
    selector: 'jhi-resposta-atividade-update',
    templateUrl: './resposta-atividade-update.component.html'
})
export class RespostaAtividadeUpdateComponent implements OnInit {
    respostaAtividade: IRespostaAtividade;
    isSaving: boolean;

    respativdescritivas: IRespAtivDescritiva[];

    respativoptativas: IRespAtivOptativa[];

    conceitos: IConceito[];

    itemacompanhamentoatividades: IItemAcompanhamentoAtividade[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private respostaAtividadeService: RespostaAtividadeService,
        private respAtivDescritivaService: RespAtivDescritivaService,
        private respAtivOptativaService: RespAtivOptativaService,
        private conceitoService: ConceitoService,
        private itemAcompanhamentoAtividadeService: ItemAcompanhamentoAtividadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ respostaAtividade }) => {
            this.respostaAtividade = respostaAtividade;
        });
        this.respAtivDescritivaService.query({ filter: 'respostaatividade-is-null' }).subscribe(
            (res: HttpResponse<IRespAtivDescritiva[]>) => {
                if (!this.respostaAtividade.respAtivDescritiva || !this.respostaAtividade.respAtivDescritiva.id) {
                    this.respativdescritivas = res.body;
                } else {
                    this.respAtivDescritivaService.find(this.respostaAtividade.respAtivDescritiva.id).subscribe(
                        (subRes: HttpResponse<IRespAtivDescritiva>) => {
                            this.respativdescritivas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.respAtivOptativaService.query({ filter: 'respostaatividade-is-null' }).subscribe(
            (res: HttpResponse<IRespAtivOptativa[]>) => {
                if (!this.respostaAtividade.respAtivOptativa || !this.respostaAtividade.respAtivOptativa.id) {
                    this.respativoptativas = res.body;
                } else {
                    this.respAtivOptativaService.find(this.respostaAtividade.respAtivOptativa.id).subscribe(
                        (subRes: HttpResponse<IRespAtivOptativa>) => {
                            this.respativoptativas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.conceitoService.query().subscribe(
            (res: HttpResponse<IConceito[]>) => {
                this.conceitos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.itemAcompanhamentoAtividadeService.query().subscribe(
            (res: HttpResponse<IItemAcompanhamentoAtividade[]>) => {
                this.itemacompanhamentoatividades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.respostaAtividade.id !== undefined) {
            this.subscribeToSaveResponse(this.respostaAtividadeService.update(this.respostaAtividade));
        } else {
            this.subscribeToSaveResponse(this.respostaAtividadeService.create(this.respostaAtividade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRespostaAtividade>>) {
        result.subscribe((res: HttpResponse<IRespostaAtividade>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRespAtivDescritivaById(index: number, item: IRespAtivDescritiva) {
        return item.id;
    }

    trackRespAtivOptativaById(index: number, item: IRespAtivOptativa) {
        return item.id;
    }

    trackConceitoById(index: number, item: IConceito) {
        return item.id;
    }

    trackItemAcompanhamentoAtividadeById(index: number, item: IItemAcompanhamentoAtividade) {
        return item.id;
    }
}
