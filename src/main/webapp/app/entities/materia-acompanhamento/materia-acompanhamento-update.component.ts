import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMateriaAcompanhamento } from 'app/shared/model/materia-acompanhamento.model';
import { MateriaAcompanhamentoService } from './materia-acompanhamento.service';
import { IAcompanhamentoEscolarAluno } from 'app/shared/model/acompanhamento-escolar-aluno.model';
import { AcompanhamentoEscolarAlunoService } from 'app/entities/acompanhamento-escolar-aluno';

@Component({
    selector: 'jhi-materia-acompanhamento-update',
    templateUrl: './materia-acompanhamento-update.component.html'
})
export class MateriaAcompanhamentoUpdateComponent implements OnInit {
    materiaAcompanhamento: IMateriaAcompanhamento;
    isSaving: boolean;

    acompanhamentoescolaralunos: IAcompanhamentoEscolarAluno[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private materiaAcompanhamentoService: MateriaAcompanhamentoService,
        private acompanhamentoEscolarAlunoService: AcompanhamentoEscolarAlunoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ materiaAcompanhamento }) => {
            this.materiaAcompanhamento = materiaAcompanhamento;
        });
        this.acompanhamentoEscolarAlunoService.query().subscribe(
            (res: HttpResponse<IAcompanhamentoEscolarAluno[]>) => {
                this.acompanhamentoescolaralunos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.materiaAcompanhamento.id !== undefined) {
            this.subscribeToSaveResponse(this.materiaAcompanhamentoService.update(this.materiaAcompanhamento));
        } else {
            this.subscribeToSaveResponse(this.materiaAcompanhamentoService.create(this.materiaAcompanhamento));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMateriaAcompanhamento>>) {
        result.subscribe(
            (res: HttpResponse<IMateriaAcompanhamento>) => this.onSaveSuccess(),
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

    trackAcompanhamentoEscolarAlunoById(index: number, item: IAcompanhamentoEscolarAluno) {
        return item.id;
    }
}
