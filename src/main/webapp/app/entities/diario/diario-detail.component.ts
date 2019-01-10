import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDiario } from 'app/shared/model/diario.model';

@Component({
    selector: 'jhi-diario-detail',
    templateUrl: './diario-detail.component.html'
})
export class DiarioDetailComponent implements OnInit {
    diario: IDiario;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ diario }) => {
            this.diario = diario;
        });
    }

    previousState() {
        window.history.back();
    }
}
