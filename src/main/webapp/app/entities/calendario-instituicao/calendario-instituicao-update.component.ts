import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';
import { CalendarioInstituicaoService } from './calendario-instituicao.service';
import { IInstituicao } from 'app/shared/model/instituicao.model';
import { InstituicaoService } from 'app/entities/instituicao';
import { IUnidade } from 'app/shared/model/unidade.model';
import { UnidadeService } from 'app/entities/unidade';

@Component({
    selector: 'jhi-calendario-instituicao-update',
    templateUrl: './calendario-instituicao-update.component.html'
})
export class CalendarioInstituicaoUpdateComponent implements OnInit {
    calendarioInstituicao: ICalendarioInstituicao;
    isSaving: boolean;

    instituicaos: IInstituicao[];

    unidades: IUnidade[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private calendarioInstituicaoService: CalendarioInstituicaoService,
        private instituicaoService: InstituicaoService,
        private unidadeService: UnidadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ calendarioInstituicao }) => {
            this.calendarioInstituicao = calendarioInstituicao;
        });
        this.instituicaoService.query().subscribe(
            (res: HttpResponse<IInstituicao[]>) => {
                this.instituicaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.unidadeService.query().subscribe(
            (res: HttpResponse<IUnidade[]>) => {
                this.unidades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.calendarioInstituicao.id !== undefined) {
            this.subscribeToSaveResponse(this.calendarioInstituicaoService.update(this.calendarioInstituicao));
        } else {
            this.subscribeToSaveResponse(this.calendarioInstituicaoService.create(this.calendarioInstituicao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICalendarioInstituicao>>) {
        result.subscribe(
            (res: HttpResponse<ICalendarioInstituicao>) => this.onSaveSuccess(),
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

    trackInstituicaoById(index: number, item: IInstituicao) {
        return item.id;
    }

    trackUnidadeById(index: number, item: IUnidade) {
        return item.id;
    }
}
