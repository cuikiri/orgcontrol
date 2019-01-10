import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPlanejamentoEnsino } from 'app/shared/model/planejamento-ensino.model';
import { PlanejamentoEnsinoService } from './planejamento-ensino.service';
import { ICalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';
import { CalendarioInstituicaoService } from 'app/entities/calendario-instituicao';

@Component({
    selector: 'jhi-planejamento-ensino-update',
    templateUrl: './planejamento-ensino-update.component.html'
})
export class PlanejamentoEnsinoUpdateComponent implements OnInit {
    planejamentoEnsino: IPlanejamentoEnsino;
    isSaving: boolean;

    calendarioinstituicaos: ICalendarioInstituicao[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private planejamentoEnsinoService: PlanejamentoEnsinoService,
        private calendarioInstituicaoService: CalendarioInstituicaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planejamentoEnsino }) => {
            this.planejamentoEnsino = planejamentoEnsino;
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
        if (this.planejamentoEnsino.id !== undefined) {
            this.subscribeToSaveResponse(this.planejamentoEnsinoService.update(this.planejamentoEnsino));
        } else {
            this.subscribeToSaveResponse(this.planejamentoEnsinoService.create(this.planejamentoEnsino));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPlanejamentoEnsino>>) {
        result.subscribe((res: HttpResponse<IPlanejamentoEnsino>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
