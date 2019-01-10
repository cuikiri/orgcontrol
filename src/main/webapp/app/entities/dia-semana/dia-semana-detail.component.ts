import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDiaSemana } from 'app/shared/model/dia-semana.model';

@Component({
    selector: 'jhi-dia-semana-detail',
    templateUrl: './dia-semana-detail.component.html'
})
export class DiaSemanaDetailComponent implements OnInit {
    diaSemana: IDiaSemana;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ diaSemana }) => {
            this.diaSemana = diaSemana;
        });
    }

    previousState() {
        window.history.back();
    }
}
