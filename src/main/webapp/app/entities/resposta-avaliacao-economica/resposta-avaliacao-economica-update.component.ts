import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IRespostaAvaliacaoEconomica } from 'app/shared/model/resposta-avaliacao-economica.model';
import { RespostaAvaliacaoEconomicaService } from './resposta-avaliacao-economica.service';
import { IRespAvalDescritivaEconomica } from 'app/shared/model/resp-aval-descritiva-economica.model';
import { RespAvalDescritivaEconomicaService } from 'app/entities/resp-aval-descritiva-economica';
import { IRespAvalOptativaEconomica } from 'app/shared/model/resp-aval-optativa-economica.model';
import { RespAvalOptativaEconomicaService } from 'app/entities/resp-aval-optativa-economica';
import { IConceito } from 'app/shared/model/conceito.model';
import { ConceitoService } from 'app/entities/conceito';
import { IItemAvaliacaoEconomica } from 'app/shared/model/item-avaliacao-economica.model';
import { ItemAvaliacaoEconomicaService } from 'app/entities/item-avaliacao-economica';

@Component({
    selector: 'jhi-resposta-avaliacao-economica-update',
    templateUrl: './resposta-avaliacao-economica-update.component.html'
})
export class RespostaAvaliacaoEconomicaUpdateComponent implements OnInit {
    respostaAvaliacaoEconomica: IRespostaAvaliacaoEconomica;
    isSaving: boolean;

    respavaldescritivaeconomicas: IRespAvalDescritivaEconomica[];

    respavaloptativaeconomicas: IRespAvalOptativaEconomica[];

    conceitos: IConceito[];

    itemavaliacaoeconomicas: IItemAvaliacaoEconomica[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private respostaAvaliacaoEconomicaService: RespostaAvaliacaoEconomicaService,
        private respAvalDescritivaEconomicaService: RespAvalDescritivaEconomicaService,
        private respAvalOptativaEconomicaService: RespAvalOptativaEconomicaService,
        private conceitoService: ConceitoService,
        private itemAvaliacaoEconomicaService: ItemAvaliacaoEconomicaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ respostaAvaliacaoEconomica }) => {
            this.respostaAvaliacaoEconomica = respostaAvaliacaoEconomica;
        });
        this.respAvalDescritivaEconomicaService.query({ filter: 'respostaavaliacaoeconomica-is-null' }).subscribe(
            (res: HttpResponse<IRespAvalDescritivaEconomica[]>) => {
                if (
                    !this.respostaAvaliacaoEconomica.respAvalDescritivaEconomica ||
                    !this.respostaAvaliacaoEconomica.respAvalDescritivaEconomica.id
                ) {
                    this.respavaldescritivaeconomicas = res.body;
                } else {
                    this.respAvalDescritivaEconomicaService.find(this.respostaAvaliacaoEconomica.respAvalDescritivaEconomica.id).subscribe(
                        (subRes: HttpResponse<IRespAvalDescritivaEconomica>) => {
                            this.respavaldescritivaeconomicas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.respAvalOptativaEconomicaService.query({ filter: 'respostaavaliacaoeconomica-is-null' }).subscribe(
            (res: HttpResponse<IRespAvalOptativaEconomica[]>) => {
                if (
                    !this.respostaAvaliacaoEconomica.respAvalOptativaEconomica ||
                    !this.respostaAvaliacaoEconomica.respAvalOptativaEconomica.id
                ) {
                    this.respavaloptativaeconomicas = res.body;
                } else {
                    this.respAvalOptativaEconomicaService.find(this.respostaAvaliacaoEconomica.respAvalOptativaEconomica.id).subscribe(
                        (subRes: HttpResponse<IRespAvalOptativaEconomica>) => {
                            this.respavaloptativaeconomicas = [subRes.body].concat(res.body);
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
        this.itemAvaliacaoEconomicaService.query().subscribe(
            (res: HttpResponse<IItemAvaliacaoEconomica[]>) => {
                this.itemavaliacaoeconomicas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.respostaAvaliacaoEconomica.id !== undefined) {
            this.subscribeToSaveResponse(this.respostaAvaliacaoEconomicaService.update(this.respostaAvaliacaoEconomica));
        } else {
            this.subscribeToSaveResponse(this.respostaAvaliacaoEconomicaService.create(this.respostaAvaliacaoEconomica));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRespostaAvaliacaoEconomica>>) {
        result.subscribe(
            (res: HttpResponse<IRespostaAvaliacaoEconomica>) => this.onSaveSuccess(),
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

    trackRespAvalDescritivaEconomicaById(index: number, item: IRespAvalDescritivaEconomica) {
        return item.id;
    }

    trackRespAvalOptativaEconomicaById(index: number, item: IRespAvalOptativaEconomica) {
        return item.id;
    }

    trackConceitoById(index: number, item: IConceito) {
        return item.id;
    }

    trackItemAvaliacaoEconomicaById(index: number, item: IItemAvaliacaoEconomica) {
        return item.id;
    }
}
