import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoContratacao } from 'app/shared/model/tipo-contratacao.model';

@Component({
    selector: 'jhi-tipo-contratacao-detail',
    templateUrl: './tipo-contratacao-detail.component.html'
})
export class TipoContratacaoDetailComponent implements OnInit {
    tipoContratacao: ITipoContratacao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoContratacao }) => {
            this.tipoContratacao = tipoContratacao;
        });
    }

    previousState() {
        window.history.back();
    }
}
