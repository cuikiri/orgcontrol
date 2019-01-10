import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IHorarioMateria } from 'app/shared/model/horario-materia.model';
import { HorarioMateriaService } from './horario-materia.service';
import { IMateria } from 'app/shared/model/materia.model';
import { MateriaService } from 'app/entities/materia';
import { IDiaSemana } from 'app/shared/model/dia-semana.model';
import { DiaSemanaService } from 'app/entities/dia-semana';
import { ITurma } from 'app/shared/model/turma.model';
import { TurmaService } from 'app/entities/turma';

@Component({
    selector: 'jhi-horario-materia-update',
    templateUrl: './horario-materia-update.component.html'
})
export class HorarioMateriaUpdateComponent implements OnInit {
    horarioMateria: IHorarioMateria;
    isSaving: boolean;

    materias: IMateria[];

    diasemanas: IDiaSemana[];

    turmas: ITurma[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private horarioMateriaService: HorarioMateriaService,
        private materiaService: MateriaService,
        private diaSemanaService: DiaSemanaService,
        private turmaService: TurmaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ horarioMateria }) => {
            this.horarioMateria = horarioMateria;
        });
        this.materiaService.query({ filter: 'horariomateria-is-null' }).subscribe(
            (res: HttpResponse<IMateria[]>) => {
                if (!this.horarioMateria.materia || !this.horarioMateria.materia.id) {
                    this.materias = res.body;
                } else {
                    this.materiaService.find(this.horarioMateria.materia.id).subscribe(
                        (subRes: HttpResponse<IMateria>) => {
                            this.materias = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.diaSemanaService.query({ filter: 'horariomateria-is-null' }).subscribe(
            (res: HttpResponse<IDiaSemana[]>) => {
                if (!this.horarioMateria.diaSemana || !this.horarioMateria.diaSemana.id) {
                    this.diasemanas = res.body;
                } else {
                    this.diaSemanaService.find(this.horarioMateria.diaSemana.id).subscribe(
                        (subRes: HttpResponse<IDiaSemana>) => {
                            this.diasemanas = [subRes.body].concat(res.body);
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
        if (this.horarioMateria.id !== undefined) {
            this.subscribeToSaveResponse(this.horarioMateriaService.update(this.horarioMateria));
        } else {
            this.subscribeToSaveResponse(this.horarioMateriaService.create(this.horarioMateria));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IHorarioMateria>>) {
        result.subscribe((res: HttpResponse<IHorarioMateria>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDiaSemanaById(index: number, item: IDiaSemana) {
        return item.id;
    }

    trackTurmaById(index: number, item: ITurma) {
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
