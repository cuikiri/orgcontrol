import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IInstituicao } from 'app/shared/model/instituicao.model';
import { InstituicaoService } from './instituicao.service';

@Component({
    selector: 'jhi-instituicao-update',
    templateUrl: './instituicao-update.component.html'
})
export class InstituicaoUpdateComponent implements OnInit {
    instituicao: IInstituicao;
    isSaving: boolean;

    constructor(private instituicaoService: InstituicaoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ instituicao }) => {
            this.instituicao = instituicao;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.instituicao.id !== undefined) {
            this.subscribeToSaveResponse(this.instituicaoService.update(this.instituicao));
        } else {
            this.subscribeToSaveResponse(this.instituicaoService.create(this.instituicao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IInstituicao>>) {
        result.subscribe((res: HttpResponse<IInstituicao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
