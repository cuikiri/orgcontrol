import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnotacao } from 'app/shared/model/anotacao.model';

@Component({
    selector: 'jhi-anotacao-detail',
    templateUrl: './anotacao-detail.component.html'
})
export class AnotacaoDetailComponent implements OnInit {
    anotacao: IAnotacao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ anotacao }) => {
            this.anotacao = anotacao;
        });
    }

    previousState() {
        window.history.back();
    }
}
