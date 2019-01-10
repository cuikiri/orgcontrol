import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoadoBiologico } from 'app/shared/model/tipoado-biologico.model';
import { TipoadoBiologicoService } from './tipoado-biologico.service';
import { IDadoBiologico } from 'app/shared/model/dado-biologico.model';
import { DadoBiologicoService } from 'app/entities/dado-biologico';

@Component({
    selector: 'jhi-tipoado-biologico-update',
    templateUrl: './tipoado-biologico-update.component.html'
})
export class TipoadoBiologicoUpdateComponent implements OnInit {
    tipoadoBiologico: ITipoadoBiologico;
    isSaving: boolean;

    dadobiologicos: IDadoBiologico[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoadoBiologicoService: TipoadoBiologicoService,
        private dadoBiologicoService: DadoBiologicoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoadoBiologico }) => {
            this.tipoadoBiologico = tipoadoBiologico;
        });
        this.dadoBiologicoService.query().subscribe(
            (res: HttpResponse<IDadoBiologico[]>) => {
                this.dadobiologicos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipoadoBiologico.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoadoBiologicoService.update(this.tipoadoBiologico));
        } else {
            this.subscribeToSaveResponse(this.tipoadoBiologicoService.create(this.tipoadoBiologico));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoadoBiologico>>) {
        result.subscribe((res: HttpResponse<ITipoadoBiologico>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDadoBiologicoById(index: number, item: IDadoBiologico) {
        return item.id;
    }
}
