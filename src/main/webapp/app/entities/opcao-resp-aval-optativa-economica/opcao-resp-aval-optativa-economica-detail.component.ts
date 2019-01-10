import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOpcaoRespAvalOptativaEconomica } from 'app/shared/model/opcao-resp-aval-optativa-economica.model';

@Component({
    selector: 'jhi-opcao-resp-aval-optativa-economica-detail',
    templateUrl: './opcao-resp-aval-optativa-economica-detail.component.html'
})
export class OpcaoRespAvalOptativaEconomicaDetailComponent implements OnInit {
    opcaoRespAvalOptativaEconomica: IOpcaoRespAvalOptativaEconomica;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ opcaoRespAvalOptativaEconomica }) => {
            this.opcaoRespAvalOptativaEconomica = opcaoRespAvalOptativaEconomica;
        });
    }

    previousState() {
        window.history.back();
    }
}
