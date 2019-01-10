import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOpcaoRespAtividade } from 'app/shared/model/opcao-resp-atividade.model';

@Component({
    selector: 'jhi-opcao-resp-atividade-detail',
    templateUrl: './opcao-resp-atividade-detail.component.html'
})
export class OpcaoRespAtividadeDetailComponent implements OnInit {
    opcaoRespAtividade: IOpcaoRespAtividade;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ opcaoRespAtividade }) => {
            this.opcaoRespAtividade = opcaoRespAtividade;
        });
    }

    previousState() {
        window.history.back();
    }
}
