import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IItemPlanejamentoEnsino } from 'app/shared/model/item-planejamento-ensino.model';
import { ItemPlanejamentoEnsinoService } from './item-planejamento-ensino.service';
import { IPlanejamentoEnsino } from 'app/shared/model/planejamento-ensino.model';
import { PlanejamentoEnsinoService } from 'app/entities/planejamento-ensino';

@Component({
    selector: 'jhi-item-planejamento-ensino-update',
    templateUrl: './item-planejamento-ensino-update.component.html'
})
export class ItemPlanejamentoEnsinoUpdateComponent implements OnInit {
    itemPlanejamentoEnsino: IItemPlanejamentoEnsino;
    isSaving: boolean;

    planejamentoensinos: IPlanejamentoEnsino[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private itemPlanejamentoEnsinoService: ItemPlanejamentoEnsinoService,
        private planejamentoEnsinoService: PlanejamentoEnsinoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ itemPlanejamentoEnsino }) => {
            this.itemPlanejamentoEnsino = itemPlanejamentoEnsino;
        });
        this.planejamentoEnsinoService.query().subscribe(
            (res: HttpResponse<IPlanejamentoEnsino[]>) => {
                this.planejamentoensinos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.itemPlanejamentoEnsino.id !== undefined) {
            this.subscribeToSaveResponse(this.itemPlanejamentoEnsinoService.update(this.itemPlanejamentoEnsino));
        } else {
            this.subscribeToSaveResponse(this.itemPlanejamentoEnsinoService.create(this.itemPlanejamentoEnsino));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IItemPlanejamentoEnsino>>) {
        result.subscribe(
            (res: HttpResponse<IItemPlanejamentoEnsino>) => this.onSaveSuccess(),
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

    trackPlanejamentoEnsinoById(index: number, item: IPlanejamentoEnsino) {
        return item.id;
    }
}
