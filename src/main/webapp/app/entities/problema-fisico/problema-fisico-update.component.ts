import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProblemaFisico } from 'app/shared/model/problema-fisico.model';
import { ProblemaFisicoService } from './problema-fisico.service';
import { IDadosMedico } from 'app/shared/model/dados-medico.model';
import { DadosMedicoService } from 'app/entities/dados-medico';

@Component({
    selector: 'jhi-problema-fisico-update',
    templateUrl: './problema-fisico-update.component.html'
})
export class ProblemaFisicoUpdateComponent implements OnInit {
    problemaFisico: IProblemaFisico;
    isSaving: boolean;

    dadosmedicos: IDadosMedico[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private problemaFisicoService: ProblemaFisicoService,
        private dadosMedicoService: DadosMedicoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ problemaFisico }) => {
            this.problemaFisico = problemaFisico;
        });
        this.dadosMedicoService.query().subscribe(
            (res: HttpResponse<IDadosMedico[]>) => {
                this.dadosmedicos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.problemaFisico.id !== undefined) {
            this.subscribeToSaveResponse(this.problemaFisicoService.update(this.problemaFisico));
        } else {
            this.subscribeToSaveResponse(this.problemaFisicoService.create(this.problemaFisico));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProblemaFisico>>) {
        result.subscribe((res: HttpResponse<IProblemaFisico>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDadosMedicoById(index: number, item: IDadosMedico) {
        return item.id;
    }
}
