import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRespostaAvaliacaoEconomica } from 'app/shared/model/resposta-avaliacao-economica.model';

@Component({
    selector: 'jhi-resposta-avaliacao-economica-detail',
    templateUrl: './resposta-avaliacao-economica-detail.component.html'
})
export class RespostaAvaliacaoEconomicaDetailComponent implements OnInit {
    respostaAvaliacaoEconomica: IRespostaAvaliacaoEconomica;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respostaAvaliacaoEconomica }) => {
            this.respostaAvaliacaoEconomica = respostaAvaliacaoEconomica;
        });
    }

    previousState() {
        window.history.back();
    }
}
