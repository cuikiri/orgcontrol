import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoAtividade } from 'app/shared/model/tipo-atividade.model';
import { TipoAtividadeService } from './tipo-atividade.service';
import { IAtividade } from 'app/shared/model/atividade.model';
import { AtividadeService } from 'app/entities/atividade';

@Component({
    selector: 'jhi-tipo-atividade-update',
    templateUrl: './tipo-atividade-update.component.html'
})
export class TipoAtividadeUpdateComponent implements OnInit {
    tipoAtividade: ITipoAtividade;
    isSaving: boolean;

    atividades: IAtividade[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoAtividadeService: TipoAtividadeService,
        private atividadeService: AtividadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoAtividade }) => {
            this.tipoAtividade = tipoAtividade;
        });
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
        if (this.tipoAtividade.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoAtividadeService.update(this.tipoAtividade));
        } else {
            this.subscribeToSaveResponse(this.tipoAtividadeService.create(this.tipoAtividade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoAtividade>>) {
        result.subscribe((res: HttpResponse<ITipoAtividade>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAtividadeById(index: number, item: IAtividade) {
        return item.id;
    }
}
