import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPeriodoAtividade } from 'app/shared/model/periodo-atividade.model';
import { PeriodoAtividadeService } from './periodo-atividade.service';
import { IParteBloco } from 'app/shared/model/parte-bloco.model';
import { ParteBlocoService } from 'app/entities/parte-bloco';
import { ITurma } from 'app/shared/model/turma.model';
import { TurmaService } from 'app/entities/turma';

@Component({
    selector: 'jhi-periodo-atividade-update',
    templateUrl: './periodo-atividade-update.component.html'
})
export class PeriodoAtividadeUpdateComponent implements OnInit {
    periodoAtividade: IPeriodoAtividade;
    isSaving: boolean;

    parteblocos: IParteBloco[];

    turmas: ITurma[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private periodoAtividadeService: PeriodoAtividadeService,
        private parteBlocoService: ParteBlocoService,
        private turmaService: TurmaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ periodoAtividade }) => {
            this.periodoAtividade = periodoAtividade;
        });
        this.parteBlocoService.query({ filter: 'periodoatividade-is-null' }).subscribe(
            (res: HttpResponse<IParteBloco[]>) => {
                if (!this.periodoAtividade.parteBloco || !this.periodoAtividade.parteBloco.id) {
                    this.parteblocos = res.body;
                } else {
                    this.parteBlocoService.find(this.periodoAtividade.parteBloco.id).subscribe(
                        (subRes: HttpResponse<IParteBloco>) => {
                            this.parteblocos = [subRes.body].concat(res.body);
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
        if (this.periodoAtividade.id !== undefined) {
            this.subscribeToSaveResponse(this.periodoAtividadeService.update(this.periodoAtividade));
        } else {
            this.subscribeToSaveResponse(this.periodoAtividadeService.create(this.periodoAtividade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPeriodoAtividade>>) {
        result.subscribe((res: HttpResponse<IPeriodoAtividade>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackParteBlocoById(index: number, item: IParteBloco) {
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
