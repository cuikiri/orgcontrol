import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMotivoDiaNaoUtil } from 'app/shared/model/motivo-dia-nao-util.model';
import { MotivoDiaNaoUtilService } from './motivo-dia-nao-util.service';
import { IDiaNaoUtil } from 'app/shared/model/dia-nao-util.model';
import { DiaNaoUtilService } from 'app/entities/dia-nao-util';

@Component({
    selector: 'jhi-motivo-dia-nao-util-update',
    templateUrl: './motivo-dia-nao-util-update.component.html'
})
export class MotivoDiaNaoUtilUpdateComponent implements OnInit {
    motivoDiaNaoUtil: IMotivoDiaNaoUtil;
    isSaving: boolean;

    dianaoutils: IDiaNaoUtil[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private motivoDiaNaoUtilService: MotivoDiaNaoUtilService,
        private diaNaoUtilService: DiaNaoUtilService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ motivoDiaNaoUtil }) => {
            this.motivoDiaNaoUtil = motivoDiaNaoUtil;
        });
        this.diaNaoUtilService.query().subscribe(
            (res: HttpResponse<IDiaNaoUtil[]>) => {
                this.dianaoutils = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.motivoDiaNaoUtil.id !== undefined) {
            this.subscribeToSaveResponse(this.motivoDiaNaoUtilService.update(this.motivoDiaNaoUtil));
        } else {
            this.subscribeToSaveResponse(this.motivoDiaNaoUtilService.create(this.motivoDiaNaoUtil));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMotivoDiaNaoUtil>>) {
        result.subscribe((res: HttpResponse<IMotivoDiaNaoUtil>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDiaNaoUtilById(index: number, item: IDiaNaoUtil) {
        return item.id;
    }
}
