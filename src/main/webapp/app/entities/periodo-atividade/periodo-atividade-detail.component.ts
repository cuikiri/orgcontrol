import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPeriodoAtividade } from 'app/shared/model/periodo-atividade.model';

@Component({
    selector: 'jhi-periodo-atividade-detail',
    templateUrl: './periodo-atividade-detail.component.html'
})
export class PeriodoAtividadeDetailComponent implements OnInit {
    periodoAtividade: IPeriodoAtividade;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ periodoAtividade }) => {
            this.periodoAtividade = periodoAtividade;
        });
    }

    previousState() {
        window.history.back();
    }
}
