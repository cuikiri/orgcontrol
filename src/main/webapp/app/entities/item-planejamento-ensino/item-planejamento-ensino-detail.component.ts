import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IItemPlanejamentoEnsino } from 'app/shared/model/item-planejamento-ensino.model';

@Component({
    selector: 'jhi-item-planejamento-ensino-detail',
    templateUrl: './item-planejamento-ensino-detail.component.html'
})
export class ItemPlanejamentoEnsinoDetailComponent implements OnInit {
    itemPlanejamentoEnsino: IItemPlanejamentoEnsino;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemPlanejamentoEnsino }) => {
            this.itemPlanejamentoEnsino = itemPlanejamentoEnsino;
        });
    }

    previousState() {
        window.history.back();
    }
}
