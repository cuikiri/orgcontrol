import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlanejamentoEnsino } from 'app/shared/model/planejamento-ensino.model';

@Component({
    selector: 'jhi-planejamento-ensino-detail',
    templateUrl: './planejamento-ensino-detail.component.html'
})
export class PlanejamentoEnsinoDetailComponent implements OnInit {
    planejamentoEnsino: IPlanejamentoEnsino;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planejamentoEnsino }) => {
            this.planejamentoEnsino = planejamentoEnsino;
        });
    }

    previousState() {
        window.history.back();
    }
}
