import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDoenca } from 'app/shared/model/doenca.model';
import { DoencaService } from './doenca.service';
import { IExameMedico } from 'app/shared/model/exame-medico.model';
import { ExameMedicoService } from 'app/entities/exame-medico';

@Component({
    selector: 'jhi-doenca-update',
    templateUrl: './doenca-update.component.html'
})
export class DoencaUpdateComponent implements OnInit {
    doenca: IDoenca;
    isSaving: boolean;

    examemedicos: IExameMedico[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private doencaService: DoencaService,
        private exameMedicoService: ExameMedicoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ doenca }) => {
            this.doenca = doenca;
        });
        this.exameMedicoService.query().subscribe(
            (res: HttpResponse<IExameMedico[]>) => {
                this.examemedicos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.doenca.id !== undefined) {
            this.subscribeToSaveResponse(this.doencaService.update(this.doenca));
        } else {
            this.subscribeToSaveResponse(this.doencaService.create(this.doenca));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDoenca>>) {
        result.subscribe((res: HttpResponse<IDoenca>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackExameMedicoById(index: number, item: IExameMedico) {
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
