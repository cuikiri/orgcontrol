import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDiaNaoUtil } from 'app/shared/model/dia-nao-util.model';

@Component({
    selector: 'jhi-dia-nao-util-detail',
    templateUrl: './dia-nao-util-detail.component.html'
})
export class DiaNaoUtilDetailComponent implements OnInit {
    diaNaoUtil: IDiaNaoUtil;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ diaNaoUtil }) => {
            this.diaNaoUtil = diaNaoUtil;
        });
    }

    previousState() {
        window.history.back();
    }
}
