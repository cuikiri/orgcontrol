import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAdvertencia } from 'app/shared/model/advertencia.model';
import { AdvertenciaService } from './advertencia.service';
import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from 'app/entities/aluno';

@Component({
    selector: 'jhi-advertencia-update',
    templateUrl: './advertencia-update.component.html'
})
export class AdvertenciaUpdateComponent implements OnInit {
    advertencia: IAdvertencia;
    isSaving: boolean;

    alunos: IAluno[];
    dataDp: any;
    dataAdvertenciaDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private advertenciaService: AdvertenciaService,
        private alunoService: AlunoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ advertencia }) => {
            this.advertencia = advertencia;
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
        if (this.advertencia.id !== undefined) {
            this.subscribeToSaveResponse(this.advertenciaService.update(this.advertencia));
        } else {
            this.subscribeToSaveResponse(this.advertenciaService.create(this.advertencia));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAdvertencia>>) {
        result.subscribe((res: HttpResponse<IAdvertencia>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
