import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPeriodoDuracao } from 'app/shared/model/periodo-duracao.model';

@Component({
    selector: 'jhi-periodo-duracao-detail',
    templateUrl: './periodo-duracao-detail.component.html'
})
export class PeriodoDuracaoDetailComponent implements OnInit {
    periodoDuracao: IPeriodoDuracao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ periodoDuracao }) => {
            this.periodoDuracao = periodoDuracao;
        });
    }

    previousState() {
        window.history.back();
    }
}
