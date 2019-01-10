import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IResponsavel } from 'app/shared/model/responsavel.model';
import { ResponsavelService } from './responsavel.service';
import { IPessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from 'app/entities/pessoa';
import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from 'app/entities/aluno';

@Component({
    selector: 'jhi-responsavel-update',
    templateUrl: './responsavel-update.component.html'
})
export class ResponsavelUpdateComponent implements OnInit {
    responsavel: IResponsavel;
    isSaving: boolean;

    pessoas: IPessoa[];

    alunos: IAluno[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private responsavelService: ResponsavelService,
        private pessoaService: PessoaService,
        private alunoService: AlunoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ responsavel }) => {
            this.responsavel = responsavel;
        });
        this.pessoaService.query({ filter: 'responsavel-is-null' }).subscribe(
            (res: HttpResponse<IPessoa[]>) => {
                if (!this.responsavel.pessoa || !this.responsavel.pessoa.id) {
                    this.pessoas = res.body;
                } else {
                    this.pessoaService.find(this.responsavel.pessoa.id).subscribe(
                        (subRes: HttpResponse<IPessoa>) => {
                            this.pessoas = [subRes.body].concat(res.body);
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
        if (this.responsavel.id !== undefined) {
            this.subscribeToSaveResponse(this.responsavelService.update(this.responsavel));
        } else {
            this.subscribeToSaveResponse(this.responsavelService.create(this.responsavel));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResponsavel>>) {
        result.subscribe((res: HttpResponse<IResponsavel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPessoaById(index: number, item: IPessoa) {
        return item.id;
    }

    trackAlunoById(index: number, item: IAluno) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
