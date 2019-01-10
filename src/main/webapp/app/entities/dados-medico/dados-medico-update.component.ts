import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IDadosMedico } from 'app/shared/model/dados-medico.model';
import { DadosMedicoService } from './dados-medico.service';
import { IDadoBiologico } from 'app/shared/model/dado-biologico.model';
import { DadoBiologicoService } from 'app/entities/dado-biologico';
import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from 'app/entities/colaborador';
import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from 'app/entities/aluno';

@Component({
    selector: 'jhi-dados-medico-update',
    templateUrl: './dados-medico-update.component.html'
})
export class DadosMedicoUpdateComponent implements OnInit {
    dadosMedico: IDadosMedico;
    isSaving: boolean;

    dadobiologicos: IDadoBiologico[];

    colaboradors: IColaborador[];

    alunos: IAluno[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private dadosMedicoService: DadosMedicoService,
        private dadoBiologicoService: DadoBiologicoService,
        private colaboradorService: ColaboradorService,
        private alunoService: AlunoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dadosMedico }) => {
            this.dadosMedico = dadosMedico;
        });
        this.dadoBiologicoService.query({ filter: 'dadosmedico-is-null' }).subscribe(
            (res: HttpResponse<IDadoBiologico[]>) => {
                if (!this.dadosMedico.dadoBiologico || !this.dadosMedico.dadoBiologico.id) {
                    this.dadobiologicos = res.body;
                } else {
                    this.dadoBiologicoService.find(this.dadosMedico.dadoBiologico.id).subscribe(
                        (subRes: HttpResponse<IDadoBiologico>) => {
                            this.dadobiologicos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.colaboradorService.query().subscribe(
            (res: HttpResponse<IColaborador[]>) => {
                this.colaboradors = res.body;
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
        if (this.dadosMedico.id !== undefined) {
            this.subscribeToSaveResponse(this.dadosMedicoService.update(this.dadosMedico));
        } else {
            this.subscribeToSaveResponse(this.dadosMedicoService.create(this.dadosMedico));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDadosMedico>>) {
        result.subscribe((res: HttpResponse<IDadosMedico>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDadoBiologicoById(index: number, item: IDadoBiologico) {
        return item.id;
    }

    trackColaboradorById(index: number, item: IColaborador) {
        return item.id;
    }

    trackAlunoById(index: number, item: IAluno) {
        return item.id;
    }
}
