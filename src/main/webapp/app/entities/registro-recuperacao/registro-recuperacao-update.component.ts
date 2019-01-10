import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IRegistroRecuperacao } from 'app/shared/model/registro-recuperacao.model';
import { RegistroRecuperacaoService } from './registro-recuperacao.service';
import { IDiario } from 'app/shared/model/diario.model';
import { DiarioService } from 'app/entities/diario';
import { IBimestre } from 'app/shared/model/bimestre.model';
import { BimestreService } from 'app/entities/bimestre';

@Component({
    selector: 'jhi-registro-recuperacao-update',
    templateUrl: './registro-recuperacao-update.component.html'
})
export class RegistroRecuperacaoUpdateComponent implements OnInit {
    registroRecuperacao: IRegistroRecuperacao;
    isSaving: boolean;

    diarios: IDiario[];

    bimestres: IBimestre[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private registroRecuperacaoService: RegistroRecuperacaoService,
        private diarioService: DiarioService,
        private bimestreService: BimestreService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ registroRecuperacao }) => {
            this.registroRecuperacao = registroRecuperacao;
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
        if (this.registroRecuperacao.id !== undefined) {
            this.subscribeToSaveResponse(this.registroRecuperacaoService.update(this.registroRecuperacao));
        } else {
            this.subscribeToSaveResponse(this.registroRecuperacaoService.create(this.registroRecuperacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRegistroRecuperacao>>) {
        result.subscribe((res: HttpResponse<IRegistroRecuperacao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
