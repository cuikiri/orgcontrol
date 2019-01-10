import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IGeneralidade } from 'app/shared/model/generalidade.model';
import { GeneralidadeService } from './generalidade.service';
import { IDiario } from 'app/shared/model/diario.model';
import { DiarioService } from 'app/entities/diario';
import { IBimestre } from 'app/shared/model/bimestre.model';
import { BimestreService } from 'app/entities/bimestre';

@Component({
    selector: 'jhi-generalidade-update',
    templateUrl: './generalidade-update.component.html'
})
export class GeneralidadeUpdateComponent implements OnInit {
    generalidade: IGeneralidade;
    isSaving: boolean;

    diarios: IDiario[];

    bimestres: IBimestre[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private generalidadeService: GeneralidadeService,
        private diarioService: DiarioService,
        private bimestreService: BimestreService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ generalidade }) => {
            this.generalidade = generalidade;
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
        if (this.generalidade.id !== undefined) {
            this.subscribeToSaveResponse(this.generalidadeService.update(this.generalidade));
        } else {
            this.subscribeToSaveResponse(this.generalidadeService.create(this.generalidade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGeneralidade>>) {
        result.subscribe((res: HttpResponse<IGeneralidade>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
