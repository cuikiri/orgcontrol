import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAcompanhamentoAluno } from 'app/shared/model/acompanhamento-aluno.model';
import { AcompanhamentoAlunoService } from './acompanhamento-aluno.service';
import { IFotoAcompanhamentoAluno } from 'app/shared/model/foto-acompanhamento-aluno.model';
import { FotoAcompanhamentoAlunoService } from 'app/entities/foto-acompanhamento-aluno';
import { ITipoAcompanhamentoAluno } from 'app/shared/model/tipo-acompanhamento-aluno.model';
import { TipoAcompanhamentoAlunoService } from 'app/entities/tipo-acompanhamento-aluno';
import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from 'app/entities/aluno';

@Component({
    selector: 'jhi-acompanhamento-aluno-update',
    templateUrl: './acompanhamento-aluno-update.component.html'
})
export class AcompanhamentoAlunoUpdateComponent implements OnInit {
    acompanhamentoAluno: IAcompanhamentoAluno;
    isSaving: boolean;

    fotoacompanhamentoalunos: IFotoAcompanhamentoAluno[];

    tipoacompanhamentoalunos: ITipoAcompanhamentoAluno[];

    alunos: IAluno[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private acompanhamentoAlunoService: AcompanhamentoAlunoService,
        private fotoAcompanhamentoAlunoService: FotoAcompanhamentoAlunoService,
        private tipoAcompanhamentoAlunoService: TipoAcompanhamentoAlunoService,
        private alunoService: AlunoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ acompanhamentoAluno }) => {
            this.acompanhamentoAluno = acompanhamentoAluno;
        });
        this.fotoAcompanhamentoAlunoService.query({ filter: 'acompanhamentoaluno-is-null' }).subscribe(
            (res: HttpResponse<IFotoAcompanhamentoAluno[]>) => {
                if (!this.acompanhamentoAluno.fotoAcompanhamentoAluno || !this.acompanhamentoAluno.fotoAcompanhamentoAluno.id) {
                    this.fotoacompanhamentoalunos = res.body;
                } else {
                    this.fotoAcompanhamentoAlunoService.find(this.acompanhamentoAluno.fotoAcompanhamentoAluno.id).subscribe(
                        (subRes: HttpResponse<IFotoAcompanhamentoAluno>) => {
                            this.fotoacompanhamentoalunos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.tipoAcompanhamentoAlunoService.query({ filter: 'acompanhamentoaluno-is-null' }).subscribe(
            (res: HttpResponse<ITipoAcompanhamentoAluno[]>) => {
                if (!this.acompanhamentoAluno.tipoAcompanhamentoAluno || !this.acompanhamentoAluno.tipoAcompanhamentoAluno.id) {
                    this.tipoacompanhamentoalunos = res.body;
                } else {
                    this.tipoAcompanhamentoAlunoService.find(this.acompanhamentoAluno.tipoAcompanhamentoAluno.id).subscribe(
                        (subRes: HttpResponse<ITipoAcompanhamentoAluno>) => {
                            this.tipoacompanhamentoalunos = [subRes.body].concat(res.body);
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
        if (this.acompanhamentoAluno.id !== undefined) {
            this.subscribeToSaveResponse(this.acompanhamentoAlunoService.update(this.acompanhamentoAluno));
        } else {
            this.subscribeToSaveResponse(this.acompanhamentoAlunoService.create(this.acompanhamentoAluno));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAcompanhamentoAluno>>) {
        result.subscribe((res: HttpResponse<IAcompanhamentoAluno>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFotoAcompanhamentoAlunoById(index: number, item: IFotoAcompanhamentoAluno) {
        return item.id;
    }

    trackTipoAcompanhamentoAlunoById(index: number, item: ITipoAcompanhamentoAluno) {
        return item.id;
    }

    trackAlunoById(index: number, item: IAluno) {
        return item.id;
    }
}
