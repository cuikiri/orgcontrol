import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoUnidade } from 'app/shared/model/tipo-unidade.model';

@Component({
    selector: 'jhi-tipo-unidade-detail',
    templateUrl: './tipo-unidade-detail.component.html'
})
export class TipoUnidadeDetailComponent implements OnInit {
    tipoUnidade: ITipoUnidade;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoUnidade }) => {
            this.tipoUnidade = tipoUnidade;
        });
    }

    previousState() {
        window.history.back();
    }
}
