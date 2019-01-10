import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAvaliacao } from 'app/shared/model/avaliacao.model';

@Component({
    selector: 'jhi-avaliacao-detail',
    templateUrl: './avaliacao-detail.component.html'
})
export class AvaliacaoDetailComponent implements OnInit {
    avaliacao: IAvaliacao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ avaliacao }) => {
            this.avaliacao = avaliacao;
        });
    }

    previousState() {
        window.history.back();
    }
}
