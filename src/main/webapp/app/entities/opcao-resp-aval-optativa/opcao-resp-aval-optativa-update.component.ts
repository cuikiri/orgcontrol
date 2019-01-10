import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IOpcaoRespAvalOptativa } from 'app/shared/model/opcao-resp-aval-optativa.model';
import { OpcaoRespAvalOptativaService } from './opcao-resp-aval-optativa.service';
import { IRespAvalOptativa } from 'app/shared/model/resp-aval-optativa.model';
import { RespAvalOptativaService } from 'app/entities/resp-aval-optativa';

@Component({
    selector: 'jhi-opcao-resp-aval-optativa-update',
    templateUrl: './opcao-resp-aval-optativa-update.component.html'
})
export class OpcaoRespAvalOptativaUpdateComponent implements OnInit {
    opcaoRespAvalOptativa: IOpcaoRespAvalOptativa;
    isSaving: boolean;

    respavaloptativas: IRespAvalOptativa[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private opcaoRespAvalOptativaService: OpcaoRespAvalOptativaService,
        private respAvalOptativaService: RespAvalOptativaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ opcaoRespAvalOptativa }) => {
            this.opcaoRespAvalOptativa = opcaoRespAvalOptativa;
        });
        this.respAvalOptativaService.query().subscribe(
            (res: HttpResponse<IRespAvalOptativa[]>) => {
                this.respavaloptativas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.opcaoRespAvalOptativa.id !== undefined) {
            this.subscribeToSaveResponse(this.opcaoRespAvalOptativaService.update(this.opcaoRespAvalOptativa));
        } else {
            this.subscribeToSaveResponse(this.opcaoRespAvalOptativaService.create(this.opcaoRespAvalOptativa));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOpcaoRespAvalOptativa>>) {
        result.subscribe(
            (res: HttpResponse<IOpcaoRespAvalOptativa>) => this.onSaveSuccess(),
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

    trackRespAvalOptativaById(index: number, item: IRespAvalOptativa) {
        return item.id;
    }
}
