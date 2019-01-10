import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IConceitoAcompanhamento } from 'app/shared/model/conceito-acompanhamento.model';
import { ConceitoAcompanhamentoService } from './conceito-acompanhamento.service';
import { IBimestreAcompanhamento } from 'app/shared/model/bimestre-acompanhamento.model';
import { BimestreAcompanhamentoService } from 'app/entities/bimestre-acompanhamento';

@Component({
    selector: 'jhi-conceito-acompanhamento-update',
    templateUrl: './conceito-acompanhamento-update.component.html'
})
export class ConceitoAcompanhamentoUpdateComponent implements OnInit {
    conceitoAcompanhamento: IConceitoAcompanhamento;
    isSaving: boolean;

    bimestreacompanhamentos: IBimestreAcompanhamento[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private conceitoAcompanhamentoService: ConceitoAcompanhamentoService,
        private bimestreAcompanhamentoService: BimestreAcompanhamentoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ conceitoAcompanhamento }) => {
            this.conceitoAcompanhamento = conceitoAcompanhamento;
        });
        this.bimestreAcompanhamentoService.query().subscribe(
            (res: HttpResponse<IBimestreAcompanhamento[]>) => {
                this.bimestreacompanhamentos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.conceitoAcompanhamento.id !== undefined) {
            this.subscribeToSaveResponse(this.conceitoAcompanhamentoService.update(this.conceitoAcompanhamento));
        } else {
            this.subscribeToSaveResponse(this.conceitoAcompanhamentoService.create(this.conceitoAcompanhamento));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConceitoAcompanhamento>>) {
        result.subscribe(
            (res: HttpResponse<IConceitoAcompanhamento>) => this.onSaveSuccess(),
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

    trackBimestreAcompanhamentoById(index: number, item: IBimestreAcompanhamento) {
        return item.id;
    }
}
