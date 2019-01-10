import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAvaliacaoEconomica } from 'app/shared/model/avaliacao-economica.model';
import { AvaliacaoEconomicaService } from './avaliacao-economica.service';
import { ITipoAvaliacaoEconomica } from 'app/shared/model/tipo-avaliacao-economica.model';
import { TipoAvaliacaoEconomicaService } from 'app/entities/tipo-avaliacao-economica';
import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from 'app/entities/aluno';

@Component({
    selector: 'jhi-avaliacao-economica-update',
    templateUrl: './avaliacao-economica-update.component.html'
})
export class AvaliacaoEconomicaUpdateComponent implements OnInit {
    avaliacaoEconomica: IAvaliacaoEconomica;
    isSaving: boolean;

    tipoavaliacaoeconomicas: ITipoAvaliacaoEconomica[];

    alunos: IAluno[];
    dataDp: any;
    dataInicioDp: any;
    dataFimDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private avaliacaoEconomicaService: AvaliacaoEconomicaService,
        private tipoAvaliacaoEconomicaService: TipoAvaliacaoEconomicaService,
        private alunoService: AlunoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ avaliacaoEconomica }) => {
            this.avaliacaoEconomica = avaliacaoEconomica;
        });
        this.tipoAvaliacaoEconomicaService.query({ filter: 'avaliacaoeconomica-is-null' }).subscribe(
            (res: HttpResponse<ITipoAvaliacaoEconomica[]>) => {
                if (!this.avaliacaoEconomica.tipoAvaliacaoEconomica || !this.avaliacaoEconomica.tipoAvaliacaoEconomica.id) {
                    this.tipoavaliacaoeconomicas = res.body;
                } else {
                    this.tipoAvaliacaoEconomicaService.find(this.avaliacaoEconomica.tipoAvaliacaoEconomica.id).subscribe(
                        (subRes: HttpResponse<ITipoAvaliacaoEconomica>) => {
                            this.tipoavaliacaoeconomicas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        if (this.avaliacaoEconomica.id !== undefined) {
            this.subscribeToSaveResponse(this.avaliacaoEconomicaService.update(this.avaliacaoEconomica));
        } else {
            this.subscribeToSaveResponse(this.avaliacaoEconomicaService.create(this.avaliacaoEconomica));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAvaliacaoEconomica>>) {
        result.subscribe((res: HttpResponse<IAvaliacaoEconomica>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTipoAvaliacaoEconomicaById(index: number, item: ITipoAvaliacaoEconomica) {
        return item.id;
    }

    trackAlunoById(index: number, item: IAluno) {
        return item.id;
    }
}
