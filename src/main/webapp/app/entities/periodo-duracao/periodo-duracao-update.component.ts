import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IPeriodoDuracao } from 'app/shared/model/periodo-duracao.model';
import { PeriodoDuracaoService } from './periodo-duracao.service';
import { ICalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';
import { CalendarioInstituicaoService } from 'app/entities/calendario-instituicao';

@Component({
    selector: 'jhi-periodo-duracao-update',
    templateUrl: './periodo-duracao-update.component.html'
})
export class PeriodoDuracaoUpdateComponent implements OnInit {
    periodoDuracao: IPeriodoDuracao;
    isSaving: boolean;

    calendarioinstituicaos: ICalendarioInstituicao[];
    dataInicioDp: any;
    dataFimDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private periodoDuracaoService: PeriodoDuracaoService,
        private calendarioInstituicaoService: CalendarioInstituicaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ periodoDuracao }) => {
            this.periodoDuracao = periodoDuracao;
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
        if (this.periodoDuracao.id !== undefined) {
            this.subscribeToSaveResponse(this.periodoDuracaoService.update(this.periodoDuracao));
        } else {
            this.subscribeToSaveResponse(this.periodoDuracaoService.create(this.periodoDuracao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPeriodoDuracao>>) {
        result.subscribe((res: HttpResponse<IPeriodoDuracao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
