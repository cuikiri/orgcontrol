import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IMatricula } from 'app/shared/model/matricula.model';
import { MatriculaService } from './matricula.service';
import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from 'app/entities/aluno';
import { ITurma } from 'app/shared/model/turma.model';
import { TurmaService } from 'app/entities/turma';

@Component({
    selector: 'jhi-matricula-update',
    templateUrl: './matricula-update.component.html'
})
export class MatriculaUpdateComponent implements OnInit {
    matricula: IMatricula;
    isSaving: boolean;

    alunos: IAluno[];

    turmas: ITurma[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private matriculaService: MatriculaService,
        private alunoService: AlunoService,
        private turmaService: TurmaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ matricula }) => {
            this.matricula = matricula;
        });
        this.alunoService.query().subscribe(
            (res: HttpResponse<IAluno[]>) => {
                this.alunos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.turmaService.query().subscribe(
            (res: HttpResponse<ITurma[]>) => {
                this.turmas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.matricula.id !== undefined) {
            this.subscribeToSaveResponse(this.matriculaService.update(this.matricula));
        } else {
            this.subscribeToSaveResponse(this.matriculaService.create(this.matricula));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMatricula>>) {
        result.subscribe((res: HttpResponse<IMatricula>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTurmaById(index: number, item: ITurma) {
        return item.id;
    }
}
