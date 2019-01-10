import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProblemaFisico } from 'app/shared/model/problema-fisico.model';

@Component({
    selector: 'jhi-problema-fisico-detail',
    templateUrl: './problema-fisico-detail.component.html'
})
export class ProblemaFisicoDetailComponent implements OnInit {
    problemaFisico: IProblemaFisico;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ problemaFisico }) => {
            this.problemaFisico = problemaFisico;
        });
    }

    previousState() {
        window.history.back();
    }
}
