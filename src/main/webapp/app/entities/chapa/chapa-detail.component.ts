import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChapa } from 'app/shared/model/chapa.model';

@Component({
    selector: 'jhi-chapa-detail',
    templateUrl: './chapa-detail.component.html'
})
export class ChapaDetailComponent implements OnInit {
    chapa: IChapa;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ chapa }) => {
            this.chapa = chapa;
        });
    }

    previousState() {
        window.history.back();
    }
}
