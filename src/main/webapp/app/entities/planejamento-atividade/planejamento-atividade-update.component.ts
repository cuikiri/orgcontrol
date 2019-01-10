import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPlanejamentoAtividade } from 'app/shared/model/planejamento-atividade.model';
import { PlanejamentoAtividadeService } from './planejamento-atividade.service';
import { ICalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';
import { CalendarioInstituicaoService } from 'app/entities/calendario-instituicao';

@Component({
    selector: 'jhi-planejamento-atividade-update',
    templateUrl: './planejamento-atividade-update.component.html'
})
export class PlanejamentoAtividadeUpdateComponent implements OnInit {
    planejamentoAtividade: IPlanejamentoAtividade;
    isSaving: boolean;

    calendarioinstituicaos: ICalendarioInstituicao[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private planejamentoAtividadeService: PlanejamentoAtividadeService,
        private calendarioInstituicaoService: CalendarioInstituicaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planejamentoAtividade }) => {
            this.planejamentoAtividade = planejamentoAtividade;
        });
        this.calendarioInstituicaoService.query().subscribe(
            (res: HttpResponse<ICalendarioInstituicao[]>) => {
                this.calendarioinstituicaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.planejamentoAtividade.id !== undefined) {
            this.subscribeToSaveResponse(this.planejamentoAtividadeService.update(this.planejamentoAtividade));
        } else {
            this.subscribeToSaveResponse(this.planejamentoAtividadeService.create(this.planejamentoAtividade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPlanejamentoAtividade>>) {
        result.subscribe(
            (res: HttpResponse<IPlanejamentoAtividade>) => this.onSaveSuccess(),
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

    trackCalendarioInstituicaoById(index: number, item: ICalendarioInstituicao) {
        return item.id;
    }
}
