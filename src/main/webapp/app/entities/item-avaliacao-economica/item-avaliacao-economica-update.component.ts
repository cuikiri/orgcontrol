import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IItemAvaliacaoEconomica } from 'app/shared/model/item-avaliacao-economica.model';
import { ItemAvaliacaoEconomicaService } from './item-avaliacao-economica.service';
import { IRespAvalDescritivaEconomica } from 'app/shared/model/resp-aval-descritiva-economica.model';
import { RespAvalDescritivaEconomicaService } from 'app/entities/resp-aval-descritiva-economica';
import { IRespAvalOptativaEconomica } from 'app/shared/model/resp-aval-optativa-economica.model';
import { RespAvalOptativaEconomicaService } from 'app/entities/resp-aval-optativa-economica';
import { IAvaliacaoEconomica } from 'app/shared/model/avaliacao-economica.model';
import { AvaliacaoEconomicaService } from 'app/entities/avaliacao-economica';

@Component({
    selector: 'jhi-item-avaliacao-economica-update',
    templateUrl: './item-avaliacao-economica-update.component.html'
})
export class ItemAvaliacaoEconomicaUpdateComponent implements OnInit {
    itemAvaliacaoEconomica: IItemAvaliacaoEconomica;
    isSaving: boolean;

    respavaldescritivaeconomicas: IRespAvalDescritivaEconomica[];

    respavaloptativaeconomicas: IRespAvalOptativaEconomica[];

    avaliacaoeconomicas: IAvaliacaoEconomica[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private itemAvaliacaoEconomicaService: ItemAvaliacaoEconomicaService,
        private respAvalDescritivaEconomicaService: RespAvalDescritivaEconomicaService,
        private respAvalOptativaEconomicaService: RespAvalOptativaEconomicaService,
        private avaliacaoEconomicaService: AvaliacaoEconomicaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ itemAvaliacaoEconomica }) => {
            this.itemAvaliacaoEconomica = itemAvaliacaoEconomica;
        });
        this.respAvalDescritivaEconomicaService.query({ filter: 'itemavaliacaoeconomica-is-null' }).subscribe(
            (res: HttpResponse<IRespAvalDescritivaEconomica[]>) => {
                if (
                    !this.itemAvaliacaoEconomica.respAvalDescritivaEconomica ||
                    !this.itemAvaliacaoEconomica.respAvalDescritivaEconomica.id
                ) {
                    this.respavaldescritivaeconomicas = res.body;
                } else {
                    this.respAvalDescritivaEconomicaService.find(this.itemAvaliacaoEconomica.respAvalDescritivaEconomica.id).subscribe(
                        (subRes: HttpResponse<IRespAvalDescritivaEconomica>) => {
                            this.respavaldescritivaeconomicas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.respAvalOptativaEconomicaService.query({ filter: 'itemavaliacaoeconomica-is-null' }).subscribe(
            (res: HttpResponse<IRespAvalOptativaEconomica[]>) => {
                if (!this.itemAvaliacaoEconomica.respAvalOptativaEconomica || !this.itemAvaliacaoEconomica.respAvalOptativaEconomica.id) {
                    this.respavaloptativaeconomicas = res.body;
                } else {
                    this.respAvalOptativaEconomicaService.find(this.itemAvaliacaoEconomica.respAvalOptativaEconomica.id).subscribe(
                        (subRes: HttpResponse<IRespAvalOptativaEconomica>) => {
                            this.respavaloptativaeconomicas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.avaliacaoEconomicaService.query().subscribe(
            (res: HttpResponse<IAvaliacaoEconomica[]>) => {
                this.avaliacaoeconomicas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.itemAvaliacaoEconomica.id !== undefined) {
            this.subscribeToSaveResponse(this.itemAvaliacaoEconomicaService.update(this.itemAvaliacaoEconomica));
        } else {
            this.subscribeToSaveResponse(this.itemAvaliacaoEconomicaService.create(this.itemAvaliacaoEconomica));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IItemAvaliacaoEconomica>>) {
        result.subscribe(
            (res: HttpResponse<IItemAvaliacaoEconomica>) => this.onSaveSuccess(),
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

    trackAvaliacaoEconomicaById(index: number, item: IAvaliacaoEconomica) {
        return item.id;
    }
}
