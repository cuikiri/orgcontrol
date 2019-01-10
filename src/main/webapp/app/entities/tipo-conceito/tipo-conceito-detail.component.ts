import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoConceito } from 'app/shared/model/tipo-conceito.model';

@Component({
    selector: 'jhi-tipo-conceito-detail',
    templateUrl: './tipo-conceito-detail.component.html'
})
export class TipoConceitoDetailComponent implements OnInit {
    tipoConceito: ITipoConceito;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoConceito }) => {
            this.tipoConceito = tipoConceito;
        });
    }

    previousState() {
        window.history.back();
    }
}
