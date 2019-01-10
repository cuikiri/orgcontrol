import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IConceito } from 'app/shared/model/conceito.model';
import { ConceitoService } from './conceito.service';
import { ITipoConceito } from 'app/shared/model/tipo-conceito.model';
import { TipoConceitoService } from 'app/entities/tipo-conceito';
import { IFechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';
import { FechamentoBimestreService } from 'app/entities/fechamento-bimestre';

@Component({
    selector: 'jhi-conceito-update',
    templateUrl: './conceito-update.component.html'
})
export class ConceitoUpdateComponent implements OnInit {
    conceito: IConceito;
    isSaving: boolean;

    tipoconceitos: ITipoConceito[];

    fechamentobimestres: IFechamentoBimestre[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private conceitoService: ConceitoService,
        private tipoConceitoService: TipoConceitoService,
        private fechamentoBimestreService: FechamentoBimestreService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ conceito }) => {
            this.conceito = conceito;
        });
        this.tipoConceitoService.query({ filter: 'conceito-is-null' }).subscribe(
            (res: HttpResponse<ITipoConceito[]>) => {
                if (!this.conceito.tipoConceito || !this.conceito.tipoConceito.id) {
                    this.tipoconceitos = res.body;
                } else {
                    this.tipoConceitoService.find(this.conceito.tipoConceito.id).subscribe(
                        (subRes: HttpResponse<ITipoConceito>) => {
                            this.tipoconceitos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.fechamentoBimestreService.query().subscribe(
            (res: HttpResponse<IFechamentoBimestre[]>) => {
                this.fechamentobimestres = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.conceito.id !== undefined) {
            this.subscribeToSaveResponse(this.conceitoService.update(this.conceito));
        } else {
            this.subscribeToSaveResponse(this.conceitoService.create(this.conceito));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConceito>>) {
        result.subscribe((res: HttpResponse<IConceito>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTipoConceitoById(index: number, item: ITipoConceito) {
        return item.id;
    }

    trackFechamentoBimestreById(index: number, item: IFechamentoBimestre) {
        return item.id;
    }
}
