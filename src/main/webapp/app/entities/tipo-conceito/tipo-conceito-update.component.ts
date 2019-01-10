import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoConceito } from 'app/shared/model/tipo-conceito.model';
import { TipoConceitoService } from './tipo-conceito.service';
import { IConceito } from 'app/shared/model/conceito.model';
import { ConceitoService } from 'app/entities/conceito';

@Component({
    selector: 'jhi-tipo-conceito-update',
    templateUrl: './tipo-conceito-update.component.html'
})
export class TipoConceitoUpdateComponent implements OnInit {
    tipoConceito: ITipoConceito;
    isSaving: boolean;

    conceitos: IConceito[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoConceitoService: TipoConceitoService,
        private conceitoService: ConceitoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoConceito }) => {
            this.tipoConceito = tipoConceito;
        });
        this.conceitoService.query().subscribe(
            (res: HttpResponse<IConceito[]>) => {
                this.conceitos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipoConceito.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoConceitoService.update(this.tipoConceito));
        } else {
            this.subscribeToSaveResponse(this.tipoConceitoService.create(this.tipoConceito));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoConceito>>) {
        result.subscribe((res: HttpResponse<ITipoConceito>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackConceitoById(index: number, item: IConceito) {
        return item.id;
    }
}
