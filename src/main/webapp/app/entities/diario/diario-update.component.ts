import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDiario } from 'app/shared/model/diario.model';
import { DiarioService } from './diario.service';
import { IMateria } from 'app/shared/model/materia.model';
import { MateriaService } from 'app/entities/materia';
import { IObservacaoProfessor } from 'app/shared/model/observacao-professor.model';
import { ObservacaoProfessorService } from 'app/entities/observacao-professor';
import { IObservacaoCoordenador } from 'app/shared/model/observacao-coordenador.model';
import { ObservacaoCoordenadorService } from 'app/entities/observacao-coordenador';
import { IRegistroRecuperacao } from 'app/shared/model/registro-recuperacao.model';
import { RegistroRecuperacaoService } from 'app/entities/registro-recuperacao';
import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from 'app/entities/colaborador';
import { ITurma } from 'app/shared/model/turma.model';
import { TurmaService } from 'app/entities/turma';

@Component({
    selector: 'jhi-diario-update',
    templateUrl: './diario-update.component.html'
})
export class DiarioUpdateComponent implements OnInit {
    diario: IDiario;
    isSaving: boolean;

    materias: IMateria[];

    observacaoprofessors: IObservacaoProfessor[];

    observacaocoordenadors: IObservacaoCoordenador[];

    registrorecuperacaos: IRegistroRecuperacao[];

    colaboradors: IColaborador[];

    turmas: ITurma[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private diarioService: DiarioService,
        private materiaService: MateriaService,
        private observacaoProfessorService: ObservacaoProfessorService,
        private observacaoCoordenadorService: ObservacaoCoordenadorService,
        private registroRecuperacaoService: RegistroRecuperacaoService,
        private colaboradorService: ColaboradorService,
        private turmaService: TurmaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ diario }) => {
            this.diario = diario;
        });
        this.materiaService.query({ filter: 'diario-is-null' }).subscribe(
            (res: HttpResponse<IMateria[]>) => {
                if (!this.diario.materia || !this.diario.materia.id) {
                    this.materias = res.body;
                } else {
                    this.materiaService.find(this.diario.materia.id).subscribe(
                        (subRes: HttpResponse<IMateria>) => {
                            this.materias = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.observacaoProfessorService.query({ filter: 'diario-is-null' }).subscribe(
            (res: HttpResponse<IObservacaoProfessor[]>) => {
                if (!this.diario.observacaoProfessor || !this.diario.observacaoProfessor.id) {
                    this.observacaoprofessors = res.body;
                } else {
                    this.observacaoProfessorService.find(this.diario.observacaoProfessor.id).subscribe(
                        (subRes: HttpResponse<IObservacaoProfessor>) => {
                            this.observacaoprofessors = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.observacaoCoordenadorService.query({ filter: 'diario-is-null' }).subscribe(
            (res: HttpResponse<IObservacaoCoordenador[]>) => {
                if (!this.diario.observacaoCoordenador || !this.diario.observacaoCoordenador.id) {
                    this.observacaocoordenadors = res.body;
                } else {
                    this.observacaoCoordenadorService.find(this.diario.observacaoCoordenador.id).subscribe(
                        (subRes: HttpResponse<IObservacaoCoordenador>) => {
                            this.observacaocoordenadors = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.registroRecuperacaoService.query({ filter: 'diario-is-null' }).subscribe(
            (res: HttpResponse<IRegistroRecuperacao[]>) => {
                if (!this.diario.registroRecuperacao || !this.diario.registroRecuperacao.id) {
                    this.registrorecuperacaos = res.body;
                } else {
                    this.registroRecuperacaoService.find(this.diario.registroRecuperacao.id).subscribe(
                        (subRes: HttpResponse<IRegistroRecuperacao>) => {
                            this.registrorecuperacaos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.colaboradorService.query({ filter: 'diario-is-null' }).subscribe(
            (res: HttpResponse<IColaborador[]>) => {
                if (!this.diario.colaborador || !this.diario.colaborador.id) {
                    this.colaboradors = res.body;
                } else {
                    this.colaboradorService.find(this.diario.colaborador.id).subscribe(
                        (subRes: HttpResponse<IColaborador>) => {
                            this.colaboradors = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
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
        if (this.diario.id !== undefined) {
            this.subscribeToSaveResponse(this.diarioService.update(this.diario));
        } else {
            this.subscribeToSaveResponse(this.diarioService.create(this.diario));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDiario>>) {
        result.subscribe((res: HttpResponse<IDiario>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackMateriaById(index: number, item: IMateria) {
        return item.id;
    }

    trackObservacaoProfessorById(index: number, item: IObservacaoProfessor) {
        return item.id;
    }

    trackObservacaoCoordenadorById(index: number, item: IObservacaoCoordenador) {
        return item.id;
    }

    trackRegistroRecuperacaoById(index: number, item: IRegistroRecuperacao) {
        return item.id;
    }

    trackColaboradorById(index: number, item: IColaborador) {
        return item.id;
    }

    trackTurmaById(index: number, item: ITurma) {
        return item.id;
    }
}
