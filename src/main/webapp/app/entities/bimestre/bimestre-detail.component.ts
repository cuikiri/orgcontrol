import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBimestre } from 'app/shared/model/bimestre.model';

@Component({
    selector: 'jhi-bimestre-detail',
    templateUrl: './bimestre-detail.component.html'
})
export class BimestreDetailComponent implements OnInit {
    bimestre: IBimestre;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bimestre }) => {
            this.bimestre = bimestre;
        });
    }

    previousState() {
        window.history.back();
    }
}
