import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBiotipo } from 'app/shared/model/biotipo.model';
import { BiotipoService } from './biotipo.service';
import { ITipoBiotipo } from 'app/shared/model/tipo-biotipo.model';
import { TipoBiotipoService } from 'app/entities/tipo-biotipo';
import { IExameMedico } from 'app/shared/model/exame-medico.model';
import { ExameMedicoService } from 'app/entities/exame-medico';

@Component({
    selector: 'jhi-biotipo-update',
    templateUrl: './biotipo-update.component.html'
})
export class BiotipoUpdateComponent implements OnInit {
    biotipo: IBiotipo;
    isSaving: boolean;

    tipobiotipos: ITipoBiotipo[];

    examemedicos: IExameMedico[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private biotipoService: BiotipoService,
        private tipoBiotipoService: TipoBiotipoService,
        private exameMedicoService: ExameMedicoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ biotipo }) => {
            this.biotipo = biotipo;
        });
        this.tipoBiotipoService.query({ filter: 'biotipo-is-null' }).subscribe(
            (res: HttpResponse<ITipoBiotipo[]>) => {
                if (!this.biotipo.tipoBiotipo || !this.biotipo.tipoBiotipo.id) {
                    this.tipobiotipos = res.body;
                } else {
                    this.tipoBiotipoService.find(this.biotipo.tipoBiotipo.id).subscribe(
                        (subRes: HttpResponse<ITipoBiotipo>) => {
                            this.tipobiotipos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        if (this.biotipo.id !== undefined) {
            this.subscribeToSaveResponse(this.biotipoService.update(this.biotipo));
        } else {
            this.subscribeToSaveResponse(this.biotipoService.create(this.biotipo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBiotipo>>) {
        result.subscribe((res: HttpResponse<IBiotipo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTipoBiotipoById(index: number, item: ITipoBiotipo) {
        return item.id;
    }

    trackExameMedicoById(index: number, item: IExameMedico) {
        return item.id;
    }
}
