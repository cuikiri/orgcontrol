import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICaracteristicasPsiquicas } from 'app/shared/model/caracteristicas-psiquicas.model';
import { CaracteristicasPsiquicasService } from './caracteristicas-psiquicas.service';
import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from 'app/entities/aluno';

@Component({
    selector: 'jhi-caracteristicas-psiquicas-update',
    templateUrl: './caracteristicas-psiquicas-update.component.html'
})
export class CaracteristicasPsiquicasUpdateComponent implements OnInit {
    caracteristicasPsiquicas: ICaracteristicasPsiquicas;
    isSaving: boolean;

    alunos: IAluno[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private caracteristicasPsiquicasService: CaracteristicasPsiquicasService,
        private alunoService: AlunoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ caracteristicasPsiquicas }) => {
            this.caracteristicasPsiquicas = caracteristicasPsiquicas;
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
        if (this.caracteristicasPsiquicas.id !== undefined) {
            this.subscribeToSaveResponse(this.caracteristicasPsiquicasService.update(this.caracteristicasPsiquicas));
        } else {
            this.subscribeToSaveResponse(this.caracteristicasPsiquicasService.create(this.caracteristicasPsiquicas));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICaracteristicasPsiquicas>>) {
        result.subscribe(
            (res: HttpResponse<ICaracteristicasPsiquicas>) => this.onSaveSuccess(),
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
