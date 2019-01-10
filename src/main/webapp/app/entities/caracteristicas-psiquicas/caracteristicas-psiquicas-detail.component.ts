import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICaracteristicasPsiquicas } from 'app/shared/model/caracteristicas-psiquicas.model';

@Component({
    selector: 'jhi-caracteristicas-psiquicas-detail',
    templateUrl: './caracteristicas-psiquicas-detail.component.html'
})
export class CaracteristicasPsiquicasDetailComponent implements OnInit {
    caracteristicasPsiquicas: ICaracteristicasPsiquicas;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ caracteristicasPsiquicas }) => {
            this.caracteristicasPsiquicas = caracteristicasPsiquicas;
        });
    }

    previousState() {
        window.history.back();
    }
}
