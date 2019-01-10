import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IBimestre } from 'app/shared/model/bimestre.model';
import { BimestreService } from './bimestre.service';
import { IFechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';
import { FechamentoBimestreService } from 'app/entities/fechamento-bimestre';
import { IDiario } from 'app/shared/model/diario.model';
import { DiarioService } from 'app/entities/diario';

@Component({
    selector: 'jhi-bimestre-update',
    templateUrl: './bimestre-update.component.html'
})
export class BimestreUpdateComponent implements OnInit {
    bimestre: IBimestre;
    isSaving: boolean;

    fechamentobimestres: IFechamentoBimestre[];

    diarios: IDiario[];
    dataInicioDp: any;
    dataFimDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private bimestreService: BimestreService,
        private fechamentoBimestreService: FechamentoBimestreService,
        private diarioService: DiarioService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bimestre }) => {
            this.bimestre = bimestre;
        });
        this.fechamentoBimestreService.query({ filter: 'bimestre-is-null' }).subscribe(
            (res: HttpResponse<IFechamentoBimestre[]>) => {
                if (!this.bimestre.fechamentoBimestre || !this.bimestre.fechamentoBimestre.id) {
                    this.fechamentobimestres = res.body;
                } else {
                    this.fechamentoBimestreService.find(this.bimestre.fechamentoBimestre.id).subscribe(
                        (subRes: HttpResponse<IFechamentoBimestre>) => {
                            this.fechamentobimestres = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.diarioService.query().subscribe(
            (res: HttpResponse<IDiario[]>) => {
                this.diarios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.bimestre.id !== undefined) {
            this.subscribeToSaveResponse(this.bimestreService.update(this.bimestre));
        } else {
            this.subscribeToSaveResponse(this.bimestreService.create(this.bimestre));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBimestre>>) {
        result.subscribe((res: HttpResponse<IBimestre>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFechamentoBimestreById(index: number, item: IFechamentoBimestre) {
        return item.id;
    }

    trackDiarioById(index: number, item: IDiario) {
        return item.id;
    }
}
