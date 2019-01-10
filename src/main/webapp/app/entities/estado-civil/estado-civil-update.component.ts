import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEstadoCivil } from 'app/shared/model/estado-civil.model';
import { EstadoCivilService } from './estado-civil.service';
import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from 'app/entities/colaborador';

@Component({
    selector: 'jhi-estado-civil-update',
    templateUrl: './estado-civil-update.component.html'
})
export class EstadoCivilUpdateComponent implements OnInit {
    estadoCivil: IEstadoCivil;
    isSaving: boolean;

    colaboradors: IColaborador[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private estadoCivilService: EstadoCivilService,
        private colaboradorService: ColaboradorService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ estadoCivil }) => {
            this.estadoCivil = estadoCivil;
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
        if (this.estadoCivil.id !== undefined) {
            this.subscribeToSaveResponse(this.estadoCivilService.update(this.estadoCivil));
        } else {
            this.subscribeToSaveResponse(this.estadoCivilService.create(this.estadoCivil));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEstadoCivil>>) {
        result.subscribe((res: HttpResponse<IEstadoCivil>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
