import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoContratacao } from 'app/shared/model/tipo-contratacao.model';
import { TipoContratacaoService } from './tipo-contratacao.service';
import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from 'app/entities/colaborador';

@Component({
    selector: 'jhi-tipo-contratacao-update',
    templateUrl: './tipo-contratacao-update.component.html'
})
export class TipoContratacaoUpdateComponent implements OnInit {
    tipoContratacao: ITipoContratacao;
    isSaving: boolean;

    colaboradors: IColaborador[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoContratacaoService: TipoContratacaoService,
        private colaboradorService: ColaboradorService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoContratacao }) => {
            this.tipoContratacao = tipoContratacao;
        });
        this.colaboradorService.query().subscribe(
            (res: HttpResponse<IColaborador[]>) => {
                this.colaboradors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipoContratacao.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoContratacaoService.update(this.tipoContratacao));
        } else {
            this.subscribeToSaveResponse(this.tipoContratacaoService.create(this.tipoContratacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoContratacao>>) {
        result.subscribe((res: HttpResponse<ITipoContratacao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackColaboradorById(index: number, item: IColaborador) {
        return item.id;
    }
}
