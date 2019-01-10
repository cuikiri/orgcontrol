import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IDiaNaoUtil } from 'app/shared/model/dia-nao-util.model';
import { DiaNaoUtilService } from './dia-nao-util.service';
import { ICalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';
import { CalendarioInstituicaoService } from 'app/entities/calendario-instituicao';

@Component({
    selector: 'jhi-dia-nao-util-update',
    templateUrl: './dia-nao-util-update.component.html'
})
export class DiaNaoUtilUpdateComponent implements OnInit {
    diaNaoUtil: IDiaNaoUtil;
    isSaving: boolean;

    calendarioinstituicaos: ICalendarioInstituicao[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private diaNaoUtilService: DiaNaoUtilService,
        private calendarioInstituicaoService: CalendarioInstituicaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ diaNaoUtil }) => {
            this.diaNaoUtil = diaNaoUtil;
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
        if (this.diaNaoUtil.id !== undefined) {
            this.subscribeToSaveResponse(this.diaNaoUtilService.update(this.diaNaoUtil));
        } else {
            this.subscribeToSaveResponse(this.diaNaoUtilService.create(this.diaNaoUtil));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDiaNaoUtil>>) {
        result.subscribe((res: HttpResponse<IDiaNaoUtil>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
