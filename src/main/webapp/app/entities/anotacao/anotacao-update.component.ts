import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAnotacao } from 'app/shared/model/anotacao.model';
import { AnotacaoService } from './anotacao.service';
import { IDiario } from 'app/shared/model/diario.model';
import { DiarioService } from 'app/entities/diario';

@Component({
    selector: 'jhi-anotacao-update',
    templateUrl: './anotacao-update.component.html'
})
export class AnotacaoUpdateComponent implements OnInit {
    anotacao: IAnotacao;
    isSaving: boolean;

    diarios: IDiario[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private anotacaoService: AnotacaoService,
        private diarioService: DiarioService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ anotacao }) => {
            this.anotacao = anotacao;
        });
        this.diarioService.query().subscribe(
            (res: HttpResponse<IDiario[]>) => {
                this.diarios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.anotacao.id !== undefined) {
            this.subscribeToSaveResponse(this.anotacaoService.update(this.anotacao));
        } else {
            this.subscribeToSaveResponse(this.anotacaoService.create(this.anotacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAnotacao>>) {
        result.subscribe((res: HttpResponse<IAnotacao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDiarioById(index: number, item: IDiario) {
        return item.id;
    }
}
