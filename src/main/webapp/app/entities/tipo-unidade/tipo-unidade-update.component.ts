import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoUnidade } from 'app/shared/model/tipo-unidade.model';
import { TipoUnidadeService } from './tipo-unidade.service';
import { IUnidade } from 'app/shared/model/unidade.model';
import { UnidadeService } from 'app/entities/unidade';

@Component({
    selector: 'jhi-tipo-unidade-update',
    templateUrl: './tipo-unidade-update.component.html'
})
export class TipoUnidadeUpdateComponent implements OnInit {
    tipoUnidade: ITipoUnidade;
    isSaving: boolean;

    unidades: IUnidade[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoUnidadeService: TipoUnidadeService,
        private unidadeService: UnidadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoUnidade }) => {
            this.tipoUnidade = tipoUnidade;
        });
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
        if (this.tipoUnidade.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoUnidadeService.update(this.tipoUnidade));
        } else {
            this.subscribeToSaveResponse(this.tipoUnidadeService.create(this.tipoUnidade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoUnidade>>) {
        result.subscribe((res: HttpResponse<ITipoUnidade>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUnidadeById(index: number, item: IUnidade) {
        return item.id;
    }
}
