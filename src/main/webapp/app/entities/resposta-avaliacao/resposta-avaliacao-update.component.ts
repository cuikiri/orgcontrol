import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IRespostaAvaliacao } from 'app/shared/model/resposta-avaliacao.model';
import { RespostaAvaliacaoService } from './resposta-avaliacao.service';
import { IRespAvalDescritiva } from 'app/shared/model/resp-aval-descritiva.model';
import { RespAvalDescritivaService } from 'app/entities/resp-aval-descritiva';
import { IRespAvalOptativa } from 'app/shared/model/resp-aval-optativa.model';
import { RespAvalOptativaService } from 'app/entities/resp-aval-optativa';
import { IConceito } from 'app/shared/model/conceito.model';
import { ConceitoService } from 'app/entities/conceito';
import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
import { ItemAvaliacaoService } from 'app/entities/item-avaliacao';

@Component({
    selector: 'jhi-resposta-avaliacao-update',
    templateUrl: './resposta-avaliacao-update.component.html'
})
export class RespostaAvaliacaoUpdateComponent implements OnInit {
    respostaAvaliacao: IRespostaAvaliacao;
    isSaving: boolean;

    respavaldescritivas: IRespAvalDescritiva[];

    respavaloptativas: IRespAvalOptativa[];

    conceitos: IConceito[];

    itemavaliacaos: IItemAvaliacao[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private respostaAvaliacaoService: RespostaAvaliacaoService,
        private respAvalDescritivaService: RespAvalDescritivaService,
        private respAvalOptativaService: RespAvalOptativaService,
        private conceitoService: ConceitoService,
        private itemAvaliacaoService: ItemAvaliacaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ respostaAvaliacao }) => {
            this.respostaAvaliacao = respostaAvaliacao;
        });
        this.respAvalDescritivaService.query({ filter: 'respostaavaliacao-is-null' }).subscribe(
            (res: HttpResponse<IRespAvalDescritiva[]>) => {
                if (!this.respostaAvaliacao.respAvalDescritiva || !this.respostaAvaliacao.respAvalDescritiva.id) {
                    this.respavaldescritivas = res.body;
                } else {
                    this.respAvalDescritivaService.find(this.respostaAvaliacao.respAvalDescritiva.id).subscribe(
                        (subRes: HttpResponse<IRespAvalDescritiva>) => {
                            this.respavaldescritivas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.respAvalOptativaService.query({ filter: 'respostaavaliacao-is-null' }).subscribe(
            (res: HttpResponse<IRespAvalOptativa[]>) => {
                if (!this.respostaAvaliacao.respAvalOptativa || !this.respostaAvaliacao.respAvalOptativa.id) {
                    this.respavaloptativas = res.body;
                } else {
                    this.respAvalOptativaService.find(this.respostaAvaliacao.respAvalOptativa.id).subscribe(
                        (subRes: HttpResponse<IRespAvalOptativa>) => {
                            this.respavaloptativas = [subRes.body].concat(res.body);
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
        this.itemAvaliacaoService.query().subscribe(
            (res: HttpResponse<IItemAvaliacao[]>) => {
                this.itemavaliacaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.respostaAvaliacao.id !== undefined) {
            this.subscribeToSaveResponse(this.respostaAvaliacaoService.update(this.respostaAvaliacao));
        } else {
            this.subscribeToSaveResponse(this.respostaAvaliacaoService.create(this.respostaAvaliacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRespostaAvaliacao>>) {
        result.subscribe((res: HttpResponse<IRespostaAvaliacao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRespAvalDescritivaById(index: number, item: IRespAvalDescritiva) {
        return item.id;
    }

    trackRespAvalOptativaById(index: number, item: IRespAvalOptativa) {
        return item.id;
    }

    trackConceitoById(index: number, item: IConceito) {
        return item.id;
    }

    trackItemAvaliacaoById(index: number, item: IItemAvaliacao) {
        return item.id;
    }
}
