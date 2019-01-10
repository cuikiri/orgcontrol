import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRespostaAtividade } from 'app/shared/model/resposta-atividade.model';

@Component({
    selector: 'jhi-resposta-atividade-detail',
    templateUrl: './resposta-atividade-detail.component.html'
})
export class RespostaAtividadeDetailComponent implements OnInit {
    respostaAtividade: IRespostaAtividade;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respostaAtividade }) => {
            this.respostaAtividade = respostaAtividade;
        });
    }

    previousState() {
        window.history.back();
    }
}
