import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAcompanhamentoAtividade } from 'app/shared/model/acompanhamento-atividade.model';

@Component({
    selector: 'jhi-acompanhamento-atividade-detail',
    templateUrl: './acompanhamento-atividade-detail.component.html'
})
export class AcompanhamentoAtividadeDetailComponent implements OnInit {
    acompanhamentoAtividade: IAcompanhamentoAtividade;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ acompanhamentoAtividade }) => {
            this.acompanhamentoAtividade = acompanhamentoAtividade;
        });
    }

    previousState() {
        window.history.back();
    }
}
