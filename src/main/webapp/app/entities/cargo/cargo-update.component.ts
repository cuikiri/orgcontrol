import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICargo } from 'app/shared/model/cargo.model';
import { CargoService } from './cargo.service';
import { ICandidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from 'app/entities/candidato';

@Component({
    selector: 'jhi-cargo-update',
    templateUrl: './cargo-update.component.html'
})
export class CargoUpdateComponent implements OnInit {
    cargo: ICargo;
    isSaving: boolean;

    candidatoes: ICandidato[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private cargoService: CargoService,
        private candidatoService: CandidatoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cargo }) => {
            this.cargo = cargo;
        });
        this.candidatoService.query().subscribe(
            (res: HttpResponse<ICandidato[]>) => {
                this.candidatoes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.cargo.id !== undefined) {
            this.subscribeToSaveResponse(this.cargoService.update(this.cargo));
        } else {
            this.subscribeToSaveResponse(this.cargoService.create(this.cargo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICargo>>) {
        result.subscribe((res: HttpResponse<ICargo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCandidatoById(index: number, item: ICandidato) {
        return item.id;
    }
}
