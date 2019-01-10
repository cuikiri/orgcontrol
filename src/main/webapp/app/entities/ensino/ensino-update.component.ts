import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEnsino } from 'app/shared/model/ensino.model';
import { EnsinoService } from './ensino.service';
import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from 'app/entities/colaborador';

@Component({
    selector: 'jhi-ensino-update',
    templateUrl: './ensino-update.component.html'
})
export class EnsinoUpdateComponent implements OnInit {
    ensino: IEnsino;
    isSaving: boolean;

    colaboradors: IColaborador[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private ensinoService: EnsinoService,
        private colaboradorService: ColaboradorService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ensino }) => {
            this.ensino = ensino;
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
        if (this.ensino.id !== undefined) {
            this.subscribeToSaveResponse(this.ensinoService.update(this.ensino));
        } else {
            this.subscribeToSaveResponse(this.ensinoService.create(this.ensino));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnsino>>) {
        result.subscribe((res: HttpResponse<IEnsino>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
