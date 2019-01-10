import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoBloco } from 'app/shared/model/tipo-bloco.model';

@Component({
    selector: 'jhi-tipo-bloco-detail',
    templateUrl: './tipo-bloco-detail.component.html'
})
export class TipoBlocoDetailComponent implements OnInit {
    tipoBloco: ITipoBloco;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoBloco }) => {
            this.tipoBloco = tipoBloco;
        });
    }

    previousState() {
        window.history.back();
    }
}
