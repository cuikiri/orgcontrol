import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConceito } from 'app/shared/model/conceito.model';

@Component({
    selector: 'jhi-conceito-detail',
    templateUrl: './conceito-detail.component.html'
})
export class ConceitoDetailComponent implements OnInit {
    conceito: IConceito;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conceito }) => {
            this.conceito = conceito;
        });
    }

    previousState() {
        window.history.back();
    }
}
