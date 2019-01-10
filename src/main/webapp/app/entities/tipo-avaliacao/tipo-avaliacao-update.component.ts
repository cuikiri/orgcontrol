import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoAvaliacao } from 'app/shared/model/tipo-avaliacao.model';
import { TipoAvaliacaoService } from './tipo-avaliacao.service';
import { IAvaliacao } from 'app/shared/model/avaliacao.model';
import { AvaliacaoService } from 'app/entities/avaliacao';

@Component({
    selector: 'jhi-tipo-avaliacao-update',
    templateUrl: './tipo-avaliacao-update.component.html'
})
export class TipoAvaliacaoUpdateComponent implements OnInit {
    tipoAvaliacao: ITipoAvaliacao;
    isSaving: boolean;

    avaliacaos: IAvaliacao[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoAvaliacaoService: TipoAvaliacaoService,
        private avaliacaoService: AvaliacaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoAvaliacao }) => {
            this.tipoAvaliacao = tipoAvaliacao;
        });
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
        if (this.tipoAvaliacao.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoAvaliacaoService.update(this.tipoAvaliacao));
        } else {
            this.subscribeToSaveResponse(this.tipoAvaliacaoService.create(this.tipoAvaliacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoAvaliacao>>) {
        result.subscribe((res: HttpResponse<ITipoAvaliacao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAvaliacaoById(index: number, item: IAvaliacao) {
        return item.id;
    }
}
