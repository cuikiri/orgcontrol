import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IRaca } from 'app/shared/model/raca.model';
import { RacaService } from './raca.service';

@Component({
    selector: 'jhi-raca-update',
    templateUrl: './raca-update.component.html'
})
export class RacaUpdateComponent implements OnInit {
    raca: IRaca;
    isSaving: boolean;

    constructor(private racaService: RacaService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ raca }) => {
            this.raca = raca;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.raca.id !== undefined) {
            this.subscribeToSaveResponse(this.racaService.update(this.raca));
        } else {
            this.subscribeToSaveResponse(this.racaService.create(this.raca));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRaca>>) {
        result.subscribe((res: HttpResponse<IRaca>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
