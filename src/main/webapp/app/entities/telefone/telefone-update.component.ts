import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITelefone } from 'app/shared/model/telefone.model';
import { TelefoneService } from './telefone.service';
import { IPessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from 'app/entities/pessoa';
import { IUnidade } from 'app/shared/model/unidade.model';
import { UnidadeService } from 'app/entities/unidade';

@Component({
    selector: 'jhi-telefone-update',
    templateUrl: './telefone-update.component.html'
})
export class TelefoneUpdateComponent implements OnInit {
    telefone: ITelefone;
    isSaving: boolean;

    pessoas: IPessoa[];

    unidades: IUnidade[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private telefoneService: TelefoneService,
        private pessoaService: PessoaService,
        private unidadeService: UnidadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ telefone }) => {
            this.telefone = telefone;
        });
        this.pessoaService.query().subscribe(
            (res: HttpResponse<IPessoa[]>) => {
                this.pessoas = res.body;
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
        if (this.telefone.id !== undefined) {
            this.subscribeToSaveResponse(this.telefoneService.update(this.telefone));
        } else {
            this.subscribeToSaveResponse(this.telefoneService.create(this.telefone));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITelefone>>) {
        result.subscribe((res: HttpResponse<ITelefone>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPessoaById(index: number, item: IPessoa) {
        return item.id;
    }

    trackUnidadeById(index: number, item: IUnidade) {
        return item.id;
    }
}
