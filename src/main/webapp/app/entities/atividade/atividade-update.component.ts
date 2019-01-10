import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAtividade } from 'app/shared/model/atividade.model';
import { AtividadeService } from './atividade.service';
import { ITipoAtividade } from 'app/shared/model/tipo-atividade.model';
import { TipoAtividadeService } from 'app/entities/tipo-atividade';
import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from 'app/entities/aluno';
import { IDiario } from 'app/shared/model/diario.model';
import { DiarioService } from 'app/entities/diario';
import { IBimestre } from 'app/shared/model/bimestre.model';
import { BimestreService } from 'app/entities/bimestre';

@Component({
    selector: 'jhi-atividade-update',
    templateUrl: './atividade-update.component.html'
})
export class AtividadeUpdateComponent implements OnInit {
    atividade: IAtividade;
    isSaving: boolean;

    tipoatividades: ITipoAtividade[];

    alunos: IAluno[];

    diarios: IDiario[];

    bimestres: IBimestre[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private atividadeService: AtividadeService,
        private tipoAtividadeService: TipoAtividadeService,
        private alunoService: AlunoService,
        private diarioService: DiarioService,
        private bimestreService: BimestreService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ atividade }) => {
            this.atividade = atividade;
        });
        this.tipoAtividadeService.query({ filter: 'atividade-is-null' }).subscribe(
            (res: HttpResponse<ITipoAtividade[]>) => {
                if (!this.atividade.tipoAtividade || !this.atividade.tipoAtividade.id) {
                    this.tipoatividades = res.body;
                } else {
                    this.tipoAtividadeService.find(this.atividade.tipoAtividade.id).subscribe(
                        (subRes: HttpResponse<ITipoAtividade>) => {
                            this.tipoatividades = [subRes.body].concat(res.body);
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
        this.diarioService.query().subscribe(
            (res: HttpResponse<IDiario[]>) => {
                this.diarios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.bimestreService.query().subscribe(
            (res: HttpResponse<IBimestre[]>) => {
                this.bimestres = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.atividade.id !== undefined) {
            this.subscribeToSaveResponse(this.atividadeService.update(this.atividade));
        } else {
            this.subscribeToSaveResponse(this.atividadeService.create(this.atividade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAtividade>>) {
        result.subscribe((res: HttpResponse<IAtividade>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTipoAtividadeById(index: number, item: ITipoAtividade) {
        return item.id;
    }

    trackAlunoById(index: number, item: IAluno) {
        return item.id;
    }

    trackDiarioById(index: number, item: IDiario) {
        return item.id;
    }

    trackBimestreById(index: number, item: IBimestre) {
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
