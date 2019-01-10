import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoDocumento } from 'app/shared/model/tipo-documento.model';
import { TipoDocumentoService } from './tipo-documento.service';
import { IDocumento } from 'app/shared/model/documento.model';
import { DocumentoService } from 'app/entities/documento';

@Component({
    selector: 'jhi-tipo-documento-update',
    templateUrl: './tipo-documento-update.component.html'
})
export class TipoDocumentoUpdateComponent implements OnInit {
    tipoDocumento: ITipoDocumento;
    isSaving: boolean;

    documentos: IDocumento[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoDocumentoService: TipoDocumentoService,
        private documentoService: DocumentoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoDocumento }) => {
            this.tipoDocumento = tipoDocumento;
        });
        this.documentoService.query().subscribe(
            (res: HttpResponse<IDocumento[]>) => {
                this.documentos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipoDocumento.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoDocumentoService.update(this.tipoDocumento));
        } else {
            this.subscribeToSaveResponse(this.tipoDocumentoService.create(this.tipoDocumento));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoDocumento>>) {
        result.subscribe((res: HttpResponse<ITipoDocumento>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDocumentoById(index: number, item: IDocumento) {
        return item.id;
    }
}
