import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPlanejamentoInstituicao } from 'app/shared/model/planejamento-instituicao.model';
import { PlanejamentoInstituicaoService } from './planejamento-instituicao.service';
import { ICalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';
import { CalendarioInstituicaoService } from 'app/entities/calendario-instituicao';

@Component({
    selector: 'jhi-planejamento-instituicao-update',
    templateUrl: './planejamento-instituicao-update.component.html'
})
export class PlanejamentoInstituicaoUpdateComponent implements OnInit {
    planejamentoInstituicao: IPlanejamentoInstituicao;
    isSaving: boolean;

    calendarioinstituicaos: ICalendarioInstituicao[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private planejamentoInstituicaoService: PlanejamentoInstituicaoService,
        private calendarioInstituicaoService: CalendarioInstituicaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planejamentoInstituicao }) => {
            this.planejamentoInstituicao = planejamentoInstituicao;
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
        if (this.planejamentoInstituicao.id !== undefined) {
            this.subscribeToSaveResponse(this.planejamentoInstituicaoService.update(this.planejamentoInstituicao));
        } else {
            this.subscribeToSaveResponse(this.planejamentoInstituicaoService.create(this.planejamentoInstituicao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPlanejamentoInstituicao>>) {
        result.subscribe(
            (res: HttpResponse<IPlanejamentoInstituicao>) => this.onSaveSuccess(),
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
