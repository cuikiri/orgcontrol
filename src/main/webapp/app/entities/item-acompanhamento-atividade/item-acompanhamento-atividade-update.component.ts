import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IItemAcompanhamentoAtividade } from 'app/shared/model/item-acompanhamento-atividade.model';
import { ItemAcompanhamentoAtividadeService } from './item-acompanhamento-atividade.service';
import { IRespAtivDescritiva } from 'app/shared/model/resp-ativ-descritiva.model';
import { RespAtivDescritivaService } from 'app/entities/resp-ativ-descritiva';
import { IRespAtivOptativa } from 'app/shared/model/resp-ativ-optativa.model';
import { RespAtivOptativaService } from 'app/entities/resp-ativ-optativa';
import { IAcompanhamentoAtividade } from 'app/shared/model/acompanhamento-atividade.model';
import { AcompanhamentoAtividadeService } from 'app/entities/acompanhamento-atividade';

@Component({
    selector: 'jhi-item-acompanhamento-atividade-update',
    templateUrl: './item-acompanhamento-atividade-update.component.html'
})
export class ItemAcompanhamentoAtividadeUpdateComponent implements OnInit {
    itemAcompanhamentoAtividade: IItemAcompanhamentoAtividade;
    isSaving: boolean;

    respativdescritivas: IRespAtivDescritiva[];

    respativoptativas: IRespAtivOptativa[];

    acompanhamentoatividades: IAcompanhamentoAtividade[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private itemAcompanhamentoAtividadeService: ItemAcompanhamentoAtividadeService,
        private respAtivDescritivaService: RespAtivDescritivaService,
        private respAtivOptativaService: RespAtivOptativaService,
        private acompanhamentoAtividadeService: AcompanhamentoAtividadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ itemAcompanhamentoAtividade }) => {
            this.itemAcompanhamentoAtividade = itemAcompanhamentoAtividade;
        });
        this.respAtivDescritivaService.query({ filter: 'itemacompanhamentoatividade-is-null' }).subscribe(
            (res: HttpResponse<IRespAtivDescritiva[]>) => {
                if (!this.itemAcompanhamentoAtividade.respAtivDescritiva || !this.itemAcompanhamentoAtividade.respAtivDescritiva.id) {
                    this.respativdescritivas = res.body;
                } else {
                    this.respAtivDescritivaService.find(this.itemAcompanhamentoAtividade.respAtivDescritiva.id).subscribe(
                        (subRes: HttpResponse<IRespAtivDescritiva>) => {
                            this.respativdescritivas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.respAtivOptativaService.query({ filter: 'itemacompanhamentoatividade-is-null' }).subscribe(
            (res: HttpResponse<IRespAtivOptativa[]>) => {
                if (!this.itemAcompanhamentoAtividade.respAtivOptativa || !this.itemAcompanhamentoAtividade.respAtivOptativa.id) {
                    this.respativoptativas = res.body;
                } else {
                    this.respAtivOptativaService.find(this.itemAcompanhamentoAtividade.respAtivOptativa.id).subscribe(
                        (subRes: HttpResponse<IRespAtivOptativa>) => {
                            this.respativoptativas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.acompanhamentoAtividadeService.query().subscribe(
            (res: HttpResponse<IAcompanhamentoAtividade[]>) => {
                this.acompanhamentoatividades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.itemAcompanhamentoAtividade.id !== undefined) {
            this.subscribeToSaveResponse(this.itemAcompanhamentoAtividadeService.update(this.itemAcompanhamentoAtividade));
        } else {
            this.subscribeToSaveResponse(this.itemAcompanhamentoAtividadeService.create(this.itemAcompanhamentoAtividade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IItemAcompanhamentoAtividade>>) {
        result.subscribe(
            (res: HttpResponse<IItemAcompanhamentoAtividade>) => this.onSaveSuccess(),
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

    trackRespAtivDescritivaById(index: number, item: IRespAtivDescritiva) {
        return item.id;
    }

    trackRespAtivOptativaById(index: number, item: IRespAtivOptativa) {
        return item.id;
    }

    trackAcompanhamentoAtividadeById(index: number, item: IAcompanhamentoAtividade) {
        return item.id;
    }
}
