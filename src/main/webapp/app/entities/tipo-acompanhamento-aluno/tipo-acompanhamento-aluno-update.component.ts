import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoAcompanhamentoAluno } from 'app/shared/model/tipo-acompanhamento-aluno.model';
import { TipoAcompanhamentoAlunoService } from './tipo-acompanhamento-aluno.service';
import { IAcompanhamentoAluno } from 'app/shared/model/acompanhamento-aluno.model';
import { AcompanhamentoAlunoService } from 'app/entities/acompanhamento-aluno';

@Component({
    selector: 'jhi-tipo-acompanhamento-aluno-update',
    templateUrl: './tipo-acompanhamento-aluno-update.component.html'
})
export class TipoAcompanhamentoAlunoUpdateComponent implements OnInit {
    tipoAcompanhamentoAluno: ITipoAcompanhamentoAluno;
    isSaving: boolean;

    acompanhamentoalunos: IAcompanhamentoAluno[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoAcompanhamentoAlunoService: TipoAcompanhamentoAlunoService,
        private acompanhamentoAlunoService: AcompanhamentoAlunoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoAcompanhamentoAluno }) => {
            this.tipoAcompanhamentoAluno = tipoAcompanhamentoAluno;
        });
        this.acompanhamentoAlunoService.query().subscribe(
            (res: HttpResponse<IAcompanhamentoAluno[]>) => {
                this.acompanhamentoalunos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipoAcompanhamentoAluno.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoAcompanhamentoAlunoService.update(this.tipoAcompanhamentoAluno));
        } else {
            this.subscribeToSaveResponse(this.tipoAcompanhamentoAlunoService.create(this.tipoAcompanhamentoAluno));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoAcompanhamentoAluno>>) {
        result.subscribe(
            (res: HttpResponse<ITipoAcompanhamentoAluno>) => this.onSaveSuccess(),
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

    trackAcompanhamentoAlunoById(index: number, item: IAcompanhamentoAluno) {
        return item.id;
    }
}
