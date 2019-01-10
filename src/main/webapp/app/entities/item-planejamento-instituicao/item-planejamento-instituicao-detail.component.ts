import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IItemPlanejamentoInstituicao } from 'app/shared/model/item-planejamento-instituicao.model';

@Component({
    selector: 'jhi-item-planejamento-instituicao-detail',
    templateUrl: './item-planejamento-instituicao-detail.component.html'
})
export class ItemPlanejamentoInstituicaoDetailComponent implements OnInit {
    itemPlanejamentoInstituicao: IItemPlanejamentoInstituicao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemPlanejamentoInstituicao }) => {
            this.itemPlanejamentoInstituicao = itemPlanejamentoInstituicao;
        });
    }

    previousState() {
        window.history.back();
    }
}
