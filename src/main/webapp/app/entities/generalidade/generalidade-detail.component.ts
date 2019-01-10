import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeneralidade } from 'app/shared/model/generalidade.model';

@Component({
    selector: 'jhi-generalidade-detail',
    templateUrl: './generalidade-detail.component.html'
})
export class GeneralidadeDetailComponent implements OnInit {
    generalidade: IGeneralidade;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ generalidade }) => {
            this.generalidade = generalidade;
        });
    }

    previousState() {
        window.history.back();
    }
}
