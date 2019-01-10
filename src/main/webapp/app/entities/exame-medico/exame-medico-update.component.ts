import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IExameMedico } from 'app/shared/model/exame-medico.model';
import { ExameMedicoService } from './exame-medico.service';
import { IDoenca } from 'app/shared/model/doenca.model';
import { DoencaService } from 'app/entities/doenca';
import { IDadosMedico } from 'app/shared/model/dados-medico.model';
import { DadosMedicoService } from 'app/entities/dados-medico';

@Component({
    selector: 'jhi-exame-medico-update',
    templateUrl: './exame-medico-update.component.html'
})
export class ExameMedicoUpdateComponent implements OnInit {
    exameMedico: IExameMedico;
    isSaving: boolean;

    doencas: IDoenca[];

    dadosmedicos: IDadosMedico[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private exameMedicoService: ExameMedicoService,
        private doencaService: DoencaService,
        private dadosMedicoService: DadosMedicoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ exameMedico }) => {
            this.exameMedico = exameMedico;
        });
        this.doencaService.query().subscribe(
            (res: HttpResponse<IDoenca[]>) => {
                this.doencas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.dadosMedicoService.query().subscribe(
            (res: HttpResponse<IDadosMedico[]>) => {
                this.dadosmedicos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.exameMedico.id !== undefined) {
            this.subscribeToSaveResponse(this.exameMedicoService.update(this.exameMedico));
        } else {
            this.subscribeToSaveResponse(this.exameMedicoService.create(this.exameMedico));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IExameMedico>>) {
        result.subscribe((res: HttpResponse<IExameMedico>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDoencaById(index: number, item: IDoenca) {
        return item.id;
    }

    trackDadosMedicoById(index: number, item: IDadosMedico) {
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
