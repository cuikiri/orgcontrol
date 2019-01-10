import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IChapa } from 'app/shared/model/chapa.model';
import { ChapaService } from './chapa.service';
import { IEleicao } from 'app/shared/model/eleicao.model';
import { EleicaoService } from 'app/entities/eleicao';

@Component({
    selector: 'jhi-chapa-update',
    templateUrl: './chapa-update.component.html'
})
export class ChapaUpdateComponent implements OnInit {
    chapa: IChapa;
    isSaving: boolean;

    eleicaos: IEleicao[];
    dataCadastroDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private chapaService: ChapaService,
        private eleicaoService: EleicaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ chapa }) => {
            this.chapa = chapa;
        });
        this.eleicaoService.query().subscribe(
            (res: HttpResponse<IEleicao[]>) => {
                this.eleicaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.chapa.id !== undefined) {
            this.subscribeToSaveResponse(this.chapaService.update(this.chapa));
        } else {
            this.subscribeToSaveResponse(this.chapaService.create(this.chapa));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IChapa>>) {
        result.subscribe((res: HttpResponse<IChapa>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackEleicaoById(index: number, item: IEleicao) {
        return item.id;
    }
}
