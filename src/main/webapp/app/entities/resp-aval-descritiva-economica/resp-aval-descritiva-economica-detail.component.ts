import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRespAvalDescritivaEconomica } from 'app/shared/model/resp-aval-descritiva-economica.model';

@Component({
    selector: 'jhi-resp-aval-descritiva-economica-detail',
    templateUrl: './resp-aval-descritiva-economica-detail.component.html'
})
export class RespAvalDescritivaEconomicaDetailComponent implements OnInit {
    respAvalDescritivaEconomica: IRespAvalDescritivaEconomica;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAvalDescritivaEconomica }) => {
            this.respAvalDescritivaEconomica = respAvalDescritivaEconomica;
        });
    }

    previousState() {
        window.history.back();
    }
}
