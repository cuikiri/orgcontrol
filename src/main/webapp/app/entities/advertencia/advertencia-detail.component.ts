import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdvertencia } from 'app/shared/model/advertencia.model';

@Component({
    selector: 'jhi-advertencia-detail',
    templateUrl: './advertencia-detail.component.html'
})
export class AdvertenciaDetailComponent implements OnInit {
    advertencia: IAdvertencia;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ advertencia }) => {
            this.advertencia = advertencia;
        });
    }

    previousState() {
        window.history.back();
    }
}
