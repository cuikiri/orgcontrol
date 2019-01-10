import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';

@Component({
    selector: 'jhi-fechamento-bimestre-detail',
    templateUrl: './fechamento-bimestre-detail.component.html'
})
export class FechamentoBimestreDetailComponent implements OnInit {
    fechamentoBimestre: IFechamentoBimestre;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fechamentoBimestre }) => {
            this.fechamentoBimestre = fechamentoBimestre;
        });
    }

    previousState() {
        window.history.back();
    }
}
