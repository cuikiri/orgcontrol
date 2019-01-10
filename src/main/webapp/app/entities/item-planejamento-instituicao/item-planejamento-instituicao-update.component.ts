import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IItemPlanejamentoInstituicao } from 'app/shared/model/item-planejamento-instituicao.model';
import { ItemPlanejamentoInstituicaoService } from './item-planejamento-instituicao.service';
import { IPlanejamentoInstituicao } from 'app/shared/model/planejamento-instituicao.model';
import { PlanejamentoInstituicaoService } from 'app/entities/planejamento-instituicao';

@Component({
    selector: 'jhi-item-planejamento-instituicao-update',
    templateUrl: './item-planejamento-instituicao-update.component.html'
})
export class ItemPlanejamentoInstituicaoUpdateComponent implements OnInit {
    itemPlanejamentoInstituicao: IItemPlanejamentoInstituicao;
    isSaving: boolean;

    planejamentoinstituicaos: IPlanejamentoInstituicao[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private itemPlanejamentoInstituicaoService: ItemPlanejamentoInstituicaoService,
        private planejamentoInstituicaoService: PlanejamentoInstituicaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ itemPlanejamentoInstituicao }) => {
            this.itemPlanejamentoInstituicao = itemPlanejamentoInstituicao;
        });
        this.planejamentoInstituicaoService.query().subscribe(
            (res: HttpResponse<IPlanejamentoInstituicao[]>) => {
                this.planejamentoinstituicaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.itemPlanejamentoInstituicao.id !== undefined) {
            this.subscribeToSaveResponse(this.itemPlanejamentoInstituicaoService.update(this.itemPlanejamentoInstituicao));
        } else {
            this.subscribeToSaveResponse(this.itemPlanejamentoInstituicaoService.create(this.itemPlanejamentoInstituicao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IItemPlanejamentoInstituicao>>) {
        result.subscribe(
            (res: HttpResponse<IItemPlanejamentoInstituicao>) => this.onSaveSuccess(),
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

    trackPlanejamentoInstituicaoById(index: number, item: IPlanejamentoInstituicao) {
        return item.id;
    }
}
