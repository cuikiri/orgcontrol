import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IConteudoProgramatico } from 'app/shared/model/conteudo-programatico.model';
import { ConteudoProgramaticoService } from './conteudo-programatico.service';
import { IAtividade } from 'app/shared/model/atividade.model';
import { AtividadeService } from 'app/entities/atividade';

@Component({
    selector: 'jhi-conteudo-programatico-update',
    templateUrl: './conteudo-programatico-update.component.html'
})
export class ConteudoProgramaticoUpdateComponent implements OnInit {
    conteudoProgramatico: IConteudoProgramatico;
    isSaving: boolean;

    atividades: IAtividade[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private conteudoProgramaticoService: ConteudoProgramaticoService,
        private atividadeService: AtividadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ conteudoProgramatico }) => {
            this.conteudoProgramatico = conteudoProgramatico;
        });
        this.atividadeService.query().subscribe(
            (res: HttpResponse<IAtividade[]>) => {
                this.atividades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.conteudoProgramatico.id !== undefined) {
            this.subscribeToSaveResponse(this.conteudoProgramaticoService.update(this.conteudoProgramatico));
        } else {
            this.subscribeToSaveResponse(this.conteudoProgramaticoService.create(this.conteudoProgramatico));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConteudoProgramatico>>) {
        result.subscribe(
            (res: HttpResponse<IConteudoProgramatico>) => this.onSaveSuccess(),
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

    trackAtividadeById(index: number, item: IAtividade) {
        return item.id;
    }
}
