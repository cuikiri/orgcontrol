import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoAcompanhamentoAtividade } from 'app/shared/model/tipo-acompanhamento-atividade.model';

@Component({
    selector: 'jhi-tipo-acompanhamento-atividade-detail',
    templateUrl: './tipo-acompanhamento-atividade-detail.component.html'
})
export class TipoAcompanhamentoAtividadeDetailComponent implements OnInit {
    tipoAcompanhamentoAtividade: ITipoAcompanhamentoAtividade;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoAcompanhamentoAtividade }) => {
            this.tipoAcompanhamentoAtividade = tipoAcompanhamentoAtividade;
        });
    }

    previousState() {
        window.history.back();
    }
}
