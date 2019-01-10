import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMotivoDiaNaoUtil } from 'app/shared/model/motivo-dia-nao-util.model';

@Component({
    selector: 'jhi-motivo-dia-nao-util-detail',
    templateUrl: './motivo-dia-nao-util-detail.component.html'
})
export class MotivoDiaNaoUtilDetailComponent implements OnInit {
    motivoDiaNaoUtil: IMotivoDiaNaoUtil;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ motivoDiaNaoUtil }) => {
            this.motivoDiaNaoUtil = motivoDiaNaoUtil;
        });
    }

    previousState() {
        window.history.back();
    }
}
