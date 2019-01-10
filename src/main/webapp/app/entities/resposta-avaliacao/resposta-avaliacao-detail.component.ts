import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRespostaAvaliacao } from 'app/shared/model/resposta-avaliacao.model';

@Component({
    selector: 'jhi-resposta-avaliacao-detail',
    templateUrl: './resposta-avaliacao-detail.component.html'
})
export class RespostaAvaliacaoDetailComponent implements OnInit {
    respostaAvaliacao: IRespostaAvaliacao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respostaAvaliacao }) => {
            this.respostaAvaliacao = respostaAvaliacao;
        });
    }

    previousState() {
        window.history.back();
    }
}
