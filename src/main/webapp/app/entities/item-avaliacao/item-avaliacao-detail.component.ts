import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';

@Component({
    selector: 'jhi-item-avaliacao-detail',
    templateUrl: './item-avaliacao-detail.component.html'
})
export class ItemAvaliacaoDetailComponent implements OnInit {
    itemAvaliacao: IItemAvaliacao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemAvaliacao }) => {
            this.itemAvaliacao = itemAvaliacao;
        });
    }

    previousState() {
        window.history.back();
    }
}
