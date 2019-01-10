import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAcompanhamentoAtividade } from 'app/shared/model/acompanhamento-atividade.model';
import { AcompanhamentoAtividadeService } from './acompanhamento-atividade.service';
import { ITipoAcompanhamentoAtividade } from 'app/shared/model/tipo-acompanhamento-atividade.model';
import { TipoAcompanhamentoAtividadeService } from 'app/entities/tipo-acompanhamento-atividade';
import { IAtividade } from 'app/shared/model/atividade.model';
import { AtividadeService } from 'app/entities/atividade';

@Component({
    selector: 'jhi-acompanhamento-atividade-update',
    templateUrl: './acompanhamento-atividade-update.component.html'
})
export class AcompanhamentoAtividadeUpdateComponent implements OnInit {
    acompanhamentoAtividade: IAcompanhamentoAtividade;
    isSaving: boolean;

    tipoacompanhamentoatividades: ITipoAcompanhamentoAtividade[];

    atividades: IAtividade[];
    dataDp: any;
    dataInicioDp: any;
    dataFimDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private acompanhamentoAtividadeService: AcompanhamentoAtividadeService,
        private tipoAcompanhamentoAtividadeService: TipoAcompanhamentoAtividadeService,
        private atividadeService: AtividadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ acompanhamentoAtividade }) => {
            this.acompanhamentoAtividade = acompanhamentoAtividade;
        });
        this.tipoAcompanhamentoAtividadeService.query({ filter: 'acompanhamentoatividade-is-null' }).subscribe(
            (res: HttpResponse<ITipoAcompanhamentoAtividade[]>) => {
                if (
                    !this.acompanhamentoAtividade.tipoAcompanhamentoAtividade ||
                    !this.acompanhamentoAtividade.tipoAcompanhamentoAtividade.id
                ) {
                    this.tipoacompanhamentoatividades = res.body;
                } else {
                    this.tipoAcompanhamentoAtividadeService.find(this.acompanhamentoAtividade.tipoAcompanhamentoAtividade.id).subscribe(
                        (subRes: HttpResponse<ITipoAcompanhamentoAtividade>) => {
                            this.tipoacompanhamentoatividades = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.atividadeService.query().subscribe(
            (res: HttpResponse<IAtividade[]>) => {
                this.atividades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.acompanhamentoAtividade.id !== undefined) {
            this.subscribeToSaveResponse(this.acompanhamentoAtividadeService.update(this.acompanhamentoAtividade));
        } else {
            this.subscribeToSaveResponse(this.acompanhamentoAtividadeService.create(this.acompanhamentoAtividade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAcompanhamentoAtividade>>) {
        result.subscribe(
            (res: HttpResponse<IAcompanhamentoAtividade>) => this.onSaveSuccess(),
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

    trackTipoAcompanhamentoAtividadeById(index: number, item: ITipoAcompanhamentoAtividade) {
        return item.id;
    }

    trackAtividadeById(index: number, item: IAtividade) {
        return item.id;
    }
}
