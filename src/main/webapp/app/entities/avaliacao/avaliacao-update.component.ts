import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAvaliacao } from 'app/shared/model/avaliacao.model';
import { AvaliacaoService } from './avaliacao.service';
import { ITipoAvaliacao } from 'app/shared/model/tipo-avaliacao.model';
import { TipoAvaliacaoService } from 'app/entities/tipo-avaliacao';
import { IFechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';
import { FechamentoBimestreService } from 'app/entities/fechamento-bimestre';

@Component({
    selector: 'jhi-avaliacao-update',
    templateUrl: './avaliacao-update.component.html'
})
export class AvaliacaoUpdateComponent implements OnInit {
    avaliacao: IAvaliacao;
    isSaving: boolean;

    tipoavaliacaos: ITipoAvaliacao[];

    fechamentobimestres: IFechamentoBimestre[];
    dataDp: any;
    dataInicioDp: any;
    dataFimDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private avaliacaoService: AvaliacaoService,
        private tipoAvaliacaoService: TipoAvaliacaoService,
        private fechamentoBimestreService: FechamentoBimestreService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ avaliacao }) => {
            this.avaliacao = avaliacao;
        });
        this.tipoAvaliacaoService.query({ filter: 'avaliacao-is-null' }).subscribe(
            (res: HttpResponse<ITipoAvaliacao[]>) => {
                if (!this.avaliacao.tipoAvaliacao || !this.avaliacao.tipoAvaliacao.id) {
                    this.tipoavaliacaos = res.body;
                } else {
                    this.tipoAvaliacaoService.find(this.avaliacao.tipoAvaliacao.id).subscribe(
                        (subRes: HttpResponse<ITipoAvaliacao>) => {
                            this.tipoavaliacaos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.fechamentoBimestreService.query().subscribe(
            (res: HttpResponse<IFechamentoBimestre[]>) => {
                this.fechamentobimestres = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.avaliacao.id !== undefined) {
            this.subscribeToSaveResponse(this.avaliacaoService.update(this.avaliacao));
        } else {
            this.subscribeToSaveResponse(this.avaliacaoService.create(this.avaliacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAvaliacao>>) {
        result.subscribe((res: HttpResponse<IAvaliacao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTipoAvaliacaoById(index: number, item: ITipoAvaliacao) {
        return item.id;
    }

    trackFechamentoBimestreById(index: number, item: IFechamentoBimestre) {
        return item.id;
    }
}
