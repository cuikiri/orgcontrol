import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITurma } from 'app/shared/model/turma.model';
import { TurmaService } from './turma.service';
import { IEnsino } from 'app/shared/model/ensino.model';
import { EnsinoService } from 'app/entities/ensino';
import { IPeriodoDuracao } from 'app/shared/model/periodo-duracao.model';
import { PeriodoDuracaoService } from 'app/entities/periodo-duracao';
import { IPeriodoSemana } from 'app/shared/model/periodo-semana.model';
import { PeriodoSemanaService } from 'app/entities/periodo-semana';
import { ITipoCurso } from 'app/shared/model/tipo-curso.model';
import { TipoCursoService } from 'app/entities/tipo-curso';
import { ICurso } from 'app/shared/model/curso.model';
import { CursoService } from 'app/entities/curso';
import { IModulo } from 'app/shared/model/modulo.model';
import { ModuloService } from 'app/entities/modulo';
import { IHorarioMateria } from 'app/shared/model/horario-materia.model';
import { HorarioMateriaService } from 'app/entities/horario-materia';
import { IPeriodoAtividade } from 'app/shared/model/periodo-atividade.model';
import { PeriodoAtividadeService } from 'app/entities/periodo-atividade';

@Component({
    selector: 'jhi-turma-update',
    templateUrl: './turma-update.component.html'
})
export class TurmaUpdateComponent implements OnInit {
    turma: ITurma;
    isSaving: boolean;

    ensinos: IEnsino[];

    periododuracaos: IPeriodoDuracao[];

    periodosemanas: IPeriodoSemana[];

    tipocursos: ITipoCurso[];

    cursos: ICurso[];

    modulos: IModulo[];

    horariomaterias: IHorarioMateria[];

    periodoatividades: IPeriodoAtividade[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private turmaService: TurmaService,
        private ensinoService: EnsinoService,
        private periodoDuracaoService: PeriodoDuracaoService,
        private periodoSemanaService: PeriodoSemanaService,
        private tipoCursoService: TipoCursoService,
        private cursoService: CursoService,
        private moduloService: ModuloService,
        private horarioMateriaService: HorarioMateriaService,
        private periodoAtividadeService: PeriodoAtividadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ turma }) => {
            this.turma = turma;
        });
        this.ensinoService.query().subscribe(
            (res: HttpResponse<IEnsino[]>) => {
                this.ensinos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.periodoDuracaoService.query().subscribe(
            (res: HttpResponse<IPeriodoDuracao[]>) => {
                this.periododuracaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.periodoSemanaService.query().subscribe(
            (res: HttpResponse<IPeriodoSemana[]>) => {
                this.periodosemanas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.tipoCursoService.query().subscribe(
            (res: HttpResponse<ITipoCurso[]>) => {
                this.tipocursos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.cursoService.query().subscribe(
            (res: HttpResponse<ICurso[]>) => {
                this.cursos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.moduloService.query().subscribe(
            (res: HttpResponse<IModulo[]>) => {
                this.modulos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.horarioMateriaService.query().subscribe(
            (res: HttpResponse<IHorarioMateria[]>) => {
                this.horariomaterias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.periodoAtividadeService.query().subscribe(
            (res: HttpResponse<IPeriodoAtividade[]>) => {
                this.periodoatividades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.turma.id !== undefined) {
            this.subscribeToSaveResponse(this.turmaService.update(this.turma));
        } else {
            this.subscribeToSaveResponse(this.turmaService.create(this.turma));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITurma>>) {
        result.subscribe((res: HttpResponse<ITurma>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackEnsinoById(index: number, item: IEnsino) {
        return item.id;
    }

    trackPeriodoDuracaoById(index: number, item: IPeriodoDuracao) {
        return item.id;
    }

    trackPeriodoSemanaById(index: number, item: IPeriodoSemana) {
        return item.id;
    }

    trackTipoCursoById(index: number, item: ITipoCurso) {
        return item.id;
    }

    trackCursoById(index: number, item: ICurso) {
        return item.id;
    }

    trackModuloById(index: number, item: IModulo) {
        return item.id;
    }

    trackHorarioMateriaById(index: number, item: IHorarioMateria) {
        return item.id;
    }

    trackPeriodoAtividadeById(index: number, item: IPeriodoAtividade) {
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
