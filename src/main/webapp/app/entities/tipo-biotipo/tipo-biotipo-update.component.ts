import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoBiotipo } from 'app/shared/model/tipo-biotipo.model';
import { TipoBiotipoService } from './tipo-biotipo.service';
import { IBiotipo } from 'app/shared/model/biotipo.model';
import { BiotipoService } from 'app/entities/biotipo';

@Component({
    selector: 'jhi-tipo-biotipo-update',
    templateUrl: './tipo-biotipo-update.component.html'
})
export class TipoBiotipoUpdateComponent implements OnInit {
    tipoBiotipo: ITipoBiotipo;
    isSaving: boolean;

    biotipos: IBiotipo[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoBiotipoService: TipoBiotipoService,
        private biotipoService: BiotipoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoBiotipo }) => {
            this.tipoBiotipo = tipoBiotipo;
        });
        this.biotipoService.query().subscribe(
            (res: HttpResponse<IBiotipo[]>) => {
                this.biotipos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipoBiotipo.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoBiotipoService.update(this.tipoBiotipo));
        } else {
            this.subscribeToSaveResponse(this.tipoBiotipoService.create(this.tipoBiotipo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoBiotipo>>) {
        result.subscribe((res: HttpResponse<ITipoBiotipo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBiotipoById(index: number, item: IBiotipo) {
        return item.id;
    }
}
