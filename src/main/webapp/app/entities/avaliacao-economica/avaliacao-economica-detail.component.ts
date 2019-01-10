import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAvaliacaoEconomica } from 'app/shared/model/avaliacao-economica.model';

@Component({
    selector: 'jhi-avaliacao-economica-detail',
    templateUrl: './avaliacao-economica-detail.component.html'
})
export class AvaliacaoEconomicaDetailComponent implements OnInit {
    avaliacaoEconomica: IAvaliacaoEconomica;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ avaliacaoEconomica }) => {
            this.avaliacaoEconomica = avaliacaoEconomica;
        });
    }

    previousState() {
        window.history.back();
    }
}
