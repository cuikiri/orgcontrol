import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoAtividade } from 'app/shared/model/tipo-atividade.model';

@Component({
    selector: 'jhi-tipo-atividade-detail',
    templateUrl: './tipo-atividade-detail.component.html'
})
export class TipoAtividadeDetailComponent implements OnInit {
    tipoAtividade: ITipoAtividade;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoAtividade }) => {
            this.tipoAtividade = tipoAtividade;
        });
    }

    previousState() {
        window.history.back();
    }
}
