import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoAcompanhamentoAtividade } from 'app/shared/model/tipo-acompanhamento-atividade.model';
import { TipoAcompanhamentoAtividadeService } from './tipo-acompanhamento-atividade.service';
import { IAcompanhamentoAtividade } from 'app/shared/model/acompanhamento-atividade.model';
import { AcompanhamentoAtividadeService } from 'app/entities/acompanhamento-atividade';

@Component({
    selector: 'jhi-tipo-acompanhamento-atividade-update',
    templateUrl: './tipo-acompanhamento-atividade-update.component.html'
})
export class TipoAcompanhamentoAtividadeUpdateComponent implements OnInit {
    tipoAcompanhamentoAtividade: ITipoAcompanhamentoAtividade;
    isSaving: boolean;

    acompanhamentoatividades: IAcompanhamentoAtividade[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoAcompanhamentoAtividadeService: TipoAcompanhamentoAtividadeService,
        private acompanhamentoAtividadeService: AcompanhamentoAtividadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoAcompanhamentoAtividade }) => {
            this.tipoAcompanhamentoAtividade = tipoAcompanhamentoAtividade;
        });
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
        if (this.tipoAcompanhamentoAtividade.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoAcompanhamentoAtividadeService.update(this.tipoAcompanhamentoAtividade));
        } else {
            this.subscribeToSaveResponse(this.tipoAcompanhamentoAtividadeService.create(this.tipoAcompanhamentoAtividade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoAcompanhamentoAtividade>>) {
        result.subscribe(
            (res: HttpResponse<ITipoAcompanhamentoAtividade>) => this.onSaveSuccess(),
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

    trackAcompanhamentoAtividadeById(index: number, item: IAcompanhamentoAtividade) {
        return item.id;
    }
}
