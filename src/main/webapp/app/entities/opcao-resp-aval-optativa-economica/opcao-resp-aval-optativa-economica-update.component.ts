import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IOpcaoRespAvalOptativaEconomica } from 'app/shared/model/opcao-resp-aval-optativa-economica.model';
import { OpcaoRespAvalOptativaEconomicaService } from './opcao-resp-aval-optativa-economica.service';
import { IRespAvalOptativaEconomica } from 'app/shared/model/resp-aval-optativa-economica.model';
import { RespAvalOptativaEconomicaService } from 'app/entities/resp-aval-optativa-economica';

@Component({
    selector: 'jhi-opcao-resp-aval-optativa-economica-update',
    templateUrl: './opcao-resp-aval-optativa-economica-update.component.html'
})
export class OpcaoRespAvalOptativaEconomicaUpdateComponent implements OnInit {
    opcaoRespAvalOptativaEconomica: IOpcaoRespAvalOptativaEconomica;
    isSaving: boolean;

    respavaloptativaeconomicas: IRespAvalOptativaEconomica[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private opcaoRespAvalOptativaEconomicaService: OpcaoRespAvalOptativaEconomicaService,
        private respAvalOptativaEconomicaService: RespAvalOptativaEconomicaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ opcaoRespAvalOptativaEconomica }) => {
            this.opcaoRespAvalOptativaEconomica = opcaoRespAvalOptativaEconomica;
        });
        this.respAvalOptativaEconomicaService.query().subscribe(
            (res: HttpResponse<IRespAvalOptativaEconomica[]>) => {
                this.respavaloptativaeconomicas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.opcaoRespAvalOptativaEconomica.id !== undefined) {
            this.subscribeToSaveResponse(this.opcaoRespAvalOptativaEconomicaService.update(this.opcaoRespAvalOptativaEconomica));
        } else {
            this.subscribeToSaveResponse(this.opcaoRespAvalOptativaEconomicaService.create(this.opcaoRespAvalOptativaEconomica));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOpcaoRespAvalOptativaEconomica>>) {
        result.subscribe(
            (res: HttpResponse<IOpcaoRespAvalOptativaEconomica>) => this.onSaveSuccess(),
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

    trackRespAvalOptativaEconomicaById(index: number, item: IRespAvalOptativaEconomica) {
        return item.id;
    }
}
