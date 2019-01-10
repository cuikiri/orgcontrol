import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
import { ItemAvaliacaoService } from './item-avaliacao.service';
import { IRespAvalDescritiva } from 'app/shared/model/resp-aval-descritiva.model';
import { RespAvalDescritivaService } from 'app/entities/resp-aval-descritiva';
import { IRespAvalOptativa } from 'app/shared/model/resp-aval-optativa.model';
import { RespAvalOptativaService } from 'app/entities/resp-aval-optativa';
import { IAvaliacao } from 'app/shared/model/avaliacao.model';
import { AvaliacaoService } from 'app/entities/avaliacao';

@Component({
    selector: 'jhi-item-avaliacao-update',
    templateUrl: './item-avaliacao-update.component.html'
})
export class ItemAvaliacaoUpdateComponent implements OnInit {
    itemAvaliacao: IItemAvaliacao;
    isSaving: boolean;

    respavaldescritivas: IRespAvalDescritiva[];

    respavaloptativas: IRespAvalOptativa[];

    avaliacaos: IAvaliacao[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private itemAvaliacaoService: ItemAvaliacaoService,
        private respAvalDescritivaService: RespAvalDescritivaService,
        private respAvalOptativaService: RespAvalOptativaService,
        private avaliacaoService: AvaliacaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ itemAvaliacao }) => {
            this.itemAvaliacao = itemAvaliacao;
        });
        this.respAvalDescritivaService.query({ filter: 'itemavaliacao-is-null' }).subscribe(
            (res: HttpResponse<IRespAvalDescritiva[]>) => {
                if (!this.itemAvaliacao.respAvalDescritiva || !this.itemAvaliacao.respAvalDescritiva.id) {
                    this.respavaldescritivas = res.body;
                } else {
                    this.respAvalDescritivaService.find(this.itemAvaliacao.respAvalDescritiva.id).subscribe(
                        (subRes: HttpResponse<IRespAvalDescritiva>) => {
                            this.respavaldescritivas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.respAvalOptativaService.query({ filter: 'itemavaliacao-is-null' }).subscribe(
            (res: HttpResponse<IRespAvalOptativa[]>) => {
                if (!this.itemAvaliacao.respAvalOptativa || !this.itemAvaliacao.respAvalOptativa.id) {
                    this.respavaloptativas = res.body;
                } else {
                    this.respAvalOptativaService.find(this.itemAvaliacao.respAvalOptativa.id).subscribe(
                        (subRes: HttpResponse<IRespAvalOptativa>) => {
                            this.respavaloptativas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.avaliacaoService.query().subscribe(
            (res: HttpResponse<IAvaliacao[]>) => {
                this.avaliacaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.itemAvaliacao.id !== undefined) {
            this.subscribeToSaveResponse(this.itemAvaliacaoService.update(this.itemAvaliacao));
        } else {
            this.subscribeToSaveResponse(this.itemAvaliacaoService.create(this.itemAvaliacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IItemAvaliacao>>) {
        result.subscribe((res: HttpResponse<IItemAvaliacao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAvaliacaoById(index: number, item: IAvaliacao) {
        return item.id;
    }
}
