import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRespAvalOptativaEconomica } from 'app/shared/model/resp-aval-optativa-economica.model';

@Component({
    selector: 'jhi-resp-aval-optativa-economica-detail',
    templateUrl: './resp-aval-optativa-economica-detail.component.html'
})
export class RespAvalOptativaEconomicaDetailComponent implements OnInit {
    respAvalOptativaEconomica: IRespAvalOptativaEconomica;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAvalOptativaEconomica }) => {
            this.respAvalOptativaEconomica = respAvalOptativaEconomica;
        });
    }

    previousState() {
        window.history.back();
    }
}
