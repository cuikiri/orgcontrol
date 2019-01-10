import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IFotoAcompanhamentoAluno } from 'app/shared/model/foto-acompanhamento-aluno.model';
import { FotoAcompanhamentoAlunoService } from './foto-acompanhamento-aluno.service';
import { IAcompanhamentoAluno } from 'app/shared/model/acompanhamento-aluno.model';
import { AcompanhamentoAlunoService } from 'app/entities/acompanhamento-aluno';

@Component({
    selector: 'jhi-foto-acompanhamento-aluno-update',
    templateUrl: './foto-acompanhamento-aluno-update.component.html'
})
export class FotoAcompanhamentoAlunoUpdateComponent implements OnInit {
    fotoAcompanhamentoAluno: IFotoAcompanhamentoAluno;
    isSaving: boolean;

    acompanhamentoalunos: IAcompanhamentoAluno[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private fotoAcompanhamentoAlunoService: FotoAcompanhamentoAlunoService,
        private acompanhamentoAlunoService: AcompanhamentoAlunoService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ fotoAcompanhamentoAluno }) => {
            this.fotoAcompanhamentoAluno = fotoAcompanhamentoAluno;
        });
        this.acompanhamentoAlunoService.query().subscribe(
            (res: HttpResponse<IAcompanhamentoAluno[]>) => {
                this.acompanhamentoalunos = res.body;
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
        this.dataUtils.clearInputImage(this.fotoAcompanhamentoAluno, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.fotoAcompanhamentoAluno.id !== undefined) {
            this.subscribeToSaveResponse(this.fotoAcompanhamentoAlunoService.update(this.fotoAcompanhamentoAluno));
        } else {
            this.subscribeToSaveResponse(this.fotoAcompanhamentoAlunoService.create(this.fotoAcompanhamentoAluno));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFotoAcompanhamentoAluno>>) {
        result.subscribe(
            (res: HttpResponse<IFotoAcompanhamentoAluno>) => this.onSaveSuccess(),
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

    trackAcompanhamentoAlunoById(index: number, item: IAcompanhamentoAluno) {
        return item.id;
    }
}
