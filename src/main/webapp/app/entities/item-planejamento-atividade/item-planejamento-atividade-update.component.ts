import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IItemPlanejamentoAtividade } from 'app/shared/model/item-planejamento-atividade.model';
import { ItemPlanejamentoAtividadeService } from './item-planejamento-atividade.service';
import { IPlanejamentoAtividade } from 'app/shared/model/planejamento-atividade.model';
import { PlanejamentoAtividadeService } from 'app/entities/planejamento-atividade';

@Component({
    selector: 'jhi-item-planejamento-atividade-update',
    templateUrl: './item-planejamento-atividade-update.component.html'
})
export class ItemPlanejamentoAtividadeUpdateComponent implements OnInit {
    itemPlanejamentoAtividade: IItemPlanejamentoAtividade;
    isSaving: boolean;

    planejamentoatividades: IPlanejamentoAtividade[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private itemPlanejamentoAtividadeService: ItemPlanejamentoAtividadeService,
        private planejamentoAtividadeService: PlanejamentoAtividadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ itemPlanejamentoAtividade }) => {
            this.itemPlanejamentoAtividade = itemPlanejamentoAtividade;
        });
        this.planejamentoAtividadeService.query().subscribe(
            (res: HttpResponse<IPlanejamentoAtividade[]>) => {
                this.planejamentoatividades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.itemPlanejamentoAtividade.id !== undefined) {
            this.subscribeToSaveResponse(this.itemPlanejamentoAtividadeService.update(this.itemPlanejamentoAtividade));
        } else {
            this.subscribeToSaveResponse(this.itemPlanejamentoAtividadeService.create(this.itemPlanejamentoAtividade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IItemPlanejamentoAtividade>>) {
        result.subscribe(
            (res: HttpResponse<IItemPlanejamentoAtividade>) => this.onSaveSuccess(),
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

    trackPlanejamentoAtividadeById(index: number, item: IPlanejamentoAtividade) {
        return item.id;
    }
}
