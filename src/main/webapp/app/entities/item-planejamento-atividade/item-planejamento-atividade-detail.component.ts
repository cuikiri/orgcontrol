import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IItemPlanejamentoAtividade } from 'app/shared/model/item-planejamento-atividade.model';

@Component({
    selector: 'jhi-item-planejamento-atividade-detail',
    templateUrl: './item-planejamento-atividade-detail.component.html'
})
export class ItemPlanejamentoAtividadeDetailComponent implements OnInit {
    itemPlanejamentoAtividade: IItemPlanejamentoAtividade;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemPlanejamentoAtividade }) => {
            this.itemPlanejamentoAtividade = itemPlanejamentoAtividade;
        });
    }

    previousState() {
        window.history.back();
    }
}
