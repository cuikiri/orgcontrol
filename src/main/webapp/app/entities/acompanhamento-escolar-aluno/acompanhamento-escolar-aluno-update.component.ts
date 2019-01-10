import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAcompanhamentoEscolarAluno } from 'app/shared/model/acompanhamento-escolar-aluno.model';
import { AcompanhamentoEscolarAlunoService } from './acompanhamento-escolar-aluno.service';
import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from 'app/entities/aluno';

@Component({
    selector: 'jhi-acompanhamento-escolar-aluno-update',
    templateUrl: './acompanhamento-escolar-aluno-update.component.html'
})
export class AcompanhamentoEscolarAlunoUpdateComponent implements OnInit {
    acompanhamentoEscolarAluno: IAcompanhamentoEscolarAluno;
    isSaving: boolean;

    alunos: IAluno[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private acompanhamentoEscolarAlunoService: AcompanhamentoEscolarAlunoService,
        private alunoService: AlunoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ acompanhamentoEscolarAluno }) => {
            this.acompanhamentoEscolarAluno = acompanhamentoEscolarAluno;
        });
        this.alunoService.query().subscribe(
            (res: HttpResponse<IAluno[]>) => {
                this.alunos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.acompanhamentoEscolarAluno.id !== undefined) {
            this.subscribeToSaveResponse(this.acompanhamentoEscolarAlunoService.update(this.acompanhamentoEscolarAluno));
        } else {
            this.subscribeToSaveResponse(this.acompanhamentoEscolarAlunoService.create(this.acompanhamentoEscolarAluno));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAcompanhamentoEscolarAluno>>) {
        result.subscribe(
            (res: HttpResponse<IAcompanhamentoEscolarAluno>) => this.onSaveSuccess(),
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

    trackAlunoById(index: number, item: IAluno) {
        return item.id;
    }
}
