import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IOpcaoRespAtividade } from 'app/shared/model/opcao-resp-atividade.model';
import { OpcaoRespAtividadeService } from './opcao-resp-atividade.service';
import { IRespAtivOptativa } from 'app/shared/model/resp-ativ-optativa.model';
import { RespAtivOptativaService } from 'app/entities/resp-ativ-optativa';

@Component({
    selector: 'jhi-opcao-resp-atividade-update',
    templateUrl: './opcao-resp-atividade-update.component.html'
})
export class OpcaoRespAtividadeUpdateComponent implements OnInit {
    opcaoRespAtividade: IOpcaoRespAtividade;
    isSaving: boolean;

    respativoptativas: IRespAtivOptativa[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private opcaoRespAtividadeService: OpcaoRespAtividadeService,
        private respAtivOptativaService: RespAtivOptativaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ opcaoRespAtividade }) => {
            this.opcaoRespAtividade = opcaoRespAtividade;
        });
        this.respAtivOptativaService.query().subscribe(
            (res: HttpResponse<IRespAtivOptativa[]>) => {
                this.respativoptativas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.opcaoRespAtividade.id !== undefined) {
            this.subscribeToSaveResponse(this.opcaoRespAtividadeService.update(this.opcaoRespAtividade));
        } else {
            this.subscribeToSaveResponse(this.opcaoRespAtividadeService.create(this.opcaoRespAtividade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOpcaoRespAtividade>>) {
        result.subscribe((res: HttpResponse<IOpcaoRespAtividade>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRespAtivOptativaById(index: number, item: IRespAtivOptativa) {
        return item.id;
    }
}
