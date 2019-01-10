import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRegistroRecuperacao } from 'app/shared/model/registro-recuperacao.model';

@Component({
    selector: 'jhi-registro-recuperacao-detail',
    templateUrl: './registro-recuperacao-detail.component.html'
})
export class RegistroRecuperacaoDetailComponent implements OnInit {
    registroRecuperacao: IRegistroRecuperacao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ registroRecuperacao }) => {
            this.registroRecuperacao = registroRecuperacao;
        });
    }

    previousState() {
        window.history.back();
    }
}
