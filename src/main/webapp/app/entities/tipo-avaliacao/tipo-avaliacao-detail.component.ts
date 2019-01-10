import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoAvaliacao } from 'app/shared/model/tipo-avaliacao.model';

@Component({
    selector: 'jhi-tipo-avaliacao-detail',
    templateUrl: './tipo-avaliacao-detail.component.html'
})
export class TipoAvaliacaoDetailComponent implements OnInit {
    tipoAvaliacao: ITipoAvaliacao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoAvaliacao }) => {
            this.tipoAvaliacao = tipoAvaliacao;
        });
    }

    previousState() {
        window.history.back();
    }
}
