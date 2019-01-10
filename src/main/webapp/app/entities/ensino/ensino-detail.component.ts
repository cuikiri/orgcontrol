import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnsino } from 'app/shared/model/ensino.model';

@Component({
    selector: 'jhi-ensino-detail',
    templateUrl: './ensino-detail.component.html'
})
export class EnsinoDetailComponent implements OnInit {
    ensino: IEnsino;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ensino }) => {
            this.ensino = ensino;
        });
    }

    previousState() {
        window.history.back();
    }
}
