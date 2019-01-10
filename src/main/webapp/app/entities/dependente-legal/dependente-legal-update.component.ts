import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IDependenteLegal } from 'app/shared/model/dependente-legal.model';
import { DependenteLegalService } from './dependente-legal.service';
import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from 'app/entities/colaborador';

@Component({
    selector: 'jhi-dependente-legal-update',
    templateUrl: './dependente-legal-update.component.html'
})
export class DependenteLegalUpdateComponent implements OnInit {
    dependenteLegal: IDependenteLegal;
    isSaving: boolean;

    colaboradors: IColaborador[];
    dataNascimentoDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private dependenteLegalService: DependenteLegalService,
        private colaboradorService: ColaboradorService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dependenteLegal }) => {
            this.dependenteLegal = dependenteLegal;
        });
        this.colaboradorService.query().subscribe(
            (res: HttpResponse<IColaborador[]>) => {
                this.colaboradors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.dependenteLegal.id !== undefined) {
            this.subscribeToSaveResponse(this.dependenteLegalService.update(this.dependenteLegal));
        } else {
            this.subscribeToSaveResponse(this.dependenteLegalService.create(this.dependenteLegal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDependenteLegal>>) {
        result.subscribe((res: HttpResponse<IDependenteLegal>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackColaboradorById(index: number, item: IColaborador) {
        return item.id;
    }
}
