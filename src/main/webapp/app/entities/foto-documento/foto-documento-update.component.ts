import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IFotoDocumento } from 'app/shared/model/foto-documento.model';
import { FotoDocumentoService } from './foto-documento.service';
import { IDocumento } from 'app/shared/model/documento.model';
import { DocumentoService } from 'app/entities/documento';

@Component({
    selector: 'jhi-foto-documento-update',
    templateUrl: './foto-documento-update.component.html'
})
export class FotoDocumentoUpdateComponent implements OnInit {
    fotoDocumento: IFotoDocumento;
    isSaving: boolean;

    documentos: IDocumento[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private fotoDocumentoService: FotoDocumentoService,
        private documentoService: DocumentoService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ fotoDocumento }) => {
            this.fotoDocumento = fotoDocumento;
        });
        this.documentoService.query().subscribe(
            (res: HttpResponse<IDocumento[]>) => {
                this.documentos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.fotoDocumento, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.fotoDocumento.id !== undefined) {
            this.subscribeToSaveResponse(this.fotoDocumentoService.update(this.fotoDocumento));
        } else {
            this.subscribeToSaveResponse(this.fotoDocumentoService.create(this.fotoDocumento));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFotoDocumento>>) {
        result.subscribe((res: HttpResponse<IFotoDocumento>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
