import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IItemAcompanhamentoAtividade } from 'app/shared/model/item-acompanhamento-atividade.model';

@Component({
    selector: 'jhi-item-acompanhamento-atividade-detail',
    templateUrl: './item-acompanhamento-atividade-detail.component.html'
})
export class ItemAcompanhamentoAtividadeDetailComponent implements OnInit {
    itemAcompanhamentoAtividade: IItemAcompanhamentoAtividade;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ itemAcompanhamentoAtividade }) => {
            this.itemAcompanhamentoAtividade = itemAcompanhamentoAtividade;
        });
    }

    previousState() {
        window.history.back();
    }
}
