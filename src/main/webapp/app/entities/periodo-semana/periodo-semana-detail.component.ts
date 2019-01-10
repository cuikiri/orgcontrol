import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPeriodoSemana } from 'app/shared/model/periodo-semana.model';

@Component({
    selector: 'jhi-periodo-semana-detail',
    templateUrl: './periodo-semana-detail.component.html'
})
export class PeriodoSemanaDetailComponent implements OnInit {
    periodoSemana: IPeriodoSemana;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ periodoSemana }) => {
            this.periodoSemana = periodoSemana;
        });
    }

    previousState() {
        window.history.back();
    }
}
