import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBimestreAcompanhamento } from 'app/shared/model/bimestre-acompanhamento.model';
import { BimestreAcompanhamentoService } from './bimestre-acompanhamento.service';
import { IMateriaAcompanhamento } from 'app/shared/model/materia-acompanhamento.model';
import { MateriaAcompanhamentoService } from 'app/entities/materia-acompanhamento';

@Component({
    selector: 'jhi-bimestre-acompanhamento-update',
    templateUrl: './bimestre-acompanhamento-update.component.html'
})
export class BimestreAcompanhamentoUpdateComponent implements OnInit {
    bimestreAcompanhamento: IBimestreAcompanhamento;
    isSaving: boolean;

    materiaacompanhamentos: IMateriaAcompanhamento[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private bimestreAcompanhamentoService: BimestreAcompanhamentoService,
        private materiaAcompanhamentoService: MateriaAcompanhamentoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bimestreAcompanhamento }) => {
            this.bimestreAcompanhamento = bimestreAcompanhamento;
        });
        this.materiaAcompanhamentoService.query().subscribe(
            (res: HttpResponse<IMateriaAcompanhamento[]>) => {
                this.materiaacompanhamentos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.bimestreAcompanhamento.id !== undefined) {
            this.subscribeToSaveResponse(this.bimestreAcompanhamentoService.update(this.bimestreAcompanhamento));
        } else {
            this.subscribeToSaveResponse(this.bimestreAcompanhamentoService.create(this.bimestreAcompanhamento));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBimestreAcompanhamento>>) {
        result.subscribe(
            (res: HttpResponse<IBimestreAcompanhamento>) => this.onSaveSuccess(),
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

    trackMateriaAcompanhamentoById(index: number, item: IMateriaAcompanhamento) {
        return item.id;
    }
}
