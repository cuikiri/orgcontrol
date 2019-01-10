import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IModulo } from 'app/shared/model/modulo.model';
import { ModuloService } from './modulo.service';

@Component({
    selector: 'jhi-modulo-update',
    templateUrl: './modulo-update.component.html'
})
export class ModuloUpdateComponent implements OnInit {
    modulo: IModulo;
    isSaving: boolean;

    constructor(private moduloService: ModuloService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ modulo }) => {
            this.modulo = modulo;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.modulo.id !== undefined) {
            this.subscribeToSaveResponse(this.moduloService.update(this.modulo));
        } else {
            this.subscribeToSaveResponse(this.moduloService.create(this.modulo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IModulo>>) {
        result.subscribe((res: HttpResponse<IModulo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
