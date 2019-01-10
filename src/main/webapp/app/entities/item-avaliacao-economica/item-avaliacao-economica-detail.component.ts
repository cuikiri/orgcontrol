import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IItemAvaliacaoEconomica } from 'app/shared/model/item-avaliacao-economica.model';

@Component({
    selector: 'jhi-item-avaliacao-economica-detail',
    templateUrl: './item-avaliacao-economica-detail.component.html'
})
export class ItemAvaliacaoEconomicaDetailComponent implements OnInit {
    itemAvaliacaoEconomica: IItemAvaliacaoEconomica;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemAvaliacaoEconomica }) => {
            this.itemAvaliacaoEconomica = itemAvaliacaoEconomica;
        });
    }

    previousState() {
        window.history.back();
    }
}
