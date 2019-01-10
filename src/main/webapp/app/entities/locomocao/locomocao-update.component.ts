import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ILocomocao } from 'app/shared/model/locomocao.model';
import { LocomocaoService } from './locomocao.service';
import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from 'app/entities/aluno';

@Component({
    selector: 'jhi-locomocao-update',
    templateUrl: './locomocao-update.component.html'
})
export class LocomocaoUpdateComponent implements OnInit {
    locomocao: ILocomocao;
    isSaving: boolean;

    alunos: IAluno[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private locomocaoService: LocomocaoService,
        private alunoService: AlunoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ locomocao }) => {
            this.locomocao = locomocao;
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
        if (this.locomocao.id !== undefined) {
            this.subscribeToSaveResponse(this.locomocaoService.update(this.locomocao));
        } else {
            this.subscribeToSaveResponse(this.locomocaoService.create(this.locomocao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILocomocao>>) {
        result.subscribe((res: HttpResponse<ILocomocao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
