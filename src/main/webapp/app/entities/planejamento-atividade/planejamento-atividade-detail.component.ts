import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlanejamentoAtividade } from 'app/shared/model/planejamento-atividade.model';

@Component({
    selector: 'jhi-planejamento-atividade-detail',
    templateUrl: './planejamento-atividade-detail.component.html'
})
export class PlanejamentoAtividadeDetailComponent implements OnInit {
    planejamentoAtividade: IPlanejamentoAtividade;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planejamentoAtividade }) => {
            this.planejamentoAtividade = planejamentoAtividade;
        });
    }

    previousState() {
        window.history.back();
    }
}
