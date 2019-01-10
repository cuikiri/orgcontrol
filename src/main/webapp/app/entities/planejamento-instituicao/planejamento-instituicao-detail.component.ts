import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlanejamentoInstituicao } from 'app/shared/model/planejamento-instituicao.model';

@Component({
    selector: 'jhi-planejamento-instituicao-detail',
    templateUrl: './planejamento-instituicao-detail.component.html'
})
export class PlanejamentoInstituicaoDetailComponent implements OnInit {
    planejamentoInstituicao: IPlanejamentoInstituicao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planejamentoInstituicao }) => {
            this.planejamentoInstituicao = planejamentoInstituicao;
        });
    }

    previousState() {
        window.history.back();
    }
}
