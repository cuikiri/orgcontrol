import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IObservacaoCoordenador } from 'app/shared/model/observacao-coordenador.model';
import { ObservacaoCoordenadorService } from './observacao-coordenador.service';
import { IDiario } from 'app/shared/model/diario.model';
import { DiarioService } from 'app/entities/diario';
import { IBimestre } from 'app/shared/model/bimestre.model';
import { BimestreService } from 'app/entities/bimestre';

@Component({
    selector: 'jhi-observacao-coordenador-update',
    templateUrl: './observacao-coordenador-update.component.html'
})
export class ObservacaoCoordenadorUpdateComponent implements OnInit {
    observacaoCoordenador: IObservacaoCoordenador;
    isSaving: boolean;

    diarios: IDiario[];

    bimestres: IBimestre[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private observacaoCoordenadorService: ObservacaoCoordenadorService,
        private diarioService: DiarioService,
        private bimestreService: BimestreService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ observacaoCoordenador }) => {
            this.observacaoCoordenador = observacaoCoordenador;
        });
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
        if (this.observacaoCoordenador.id !== undefined) {
            this.subscribeToSaveResponse(this.observacaoCoordenadorService.update(this.observacaoCoordenador));
        } else {
            this.subscribeToSaveResponse(this.observacaoCoordenadorService.create(this.observacaoCoordenador));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IObservacaoCoordenador>>) {
        result.subscribe(
            (res: HttpResponse<IObservacaoCoordenador>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackDiarioById(index: number, item: IDiario) {
        return item.id;
    }

    trackBimestreById(index: number, item: IBimestre) {
        return item.id;
    }
}
