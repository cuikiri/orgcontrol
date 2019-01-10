import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoAvaliacaoEconomica } from 'app/shared/model/tipo-avaliacao-economica.model';

@Component({
    selector: 'jhi-tipo-avaliacao-economica-detail',
    templateUrl: './tipo-avaliacao-economica-detail.component.html'
})
export class TipoAvaliacaoEconomicaDetailComponent implements OnInit {
    tipoAvaliacaoEconomica: ITipoAvaliacaoEconomica;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoAvaliacaoEconomica }) => {
            this.tipoAvaliacaoEconomica = tipoAvaliacaoEconomica;
        });
    }

    previousState() {
        window.history.back();
    }
}
