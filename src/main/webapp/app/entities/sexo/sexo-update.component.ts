import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISexo } from 'app/shared/model/sexo.model';
import { SexoService } from './sexo.service';

@Component({
    selector: 'jhi-sexo-update',
    templateUrl: './sexo-update.component.html'
})
export class SexoUpdateComponent implements OnInit {
    sexo: ISexo;
    isSaving: boolean;

    constructor(private sexoService: SexoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sexo }) => {
            this.sexo = sexo;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sexo.id !== undefined) {
            this.subscribeToSaveResponse(this.sexoService.update(this.sexo));
        } else {
            this.subscribeToSaveResponse(this.sexoService.create(this.sexo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISexo>>) {
        result.subscribe((res: HttpResponse<ISexo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
