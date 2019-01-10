import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAcompanhamentoAluno } from 'app/shared/model/acompanhamento-aluno.model';

@Component({
    selector: 'jhi-acompanhamento-aluno-detail',
    templateUrl: './acompanhamento-aluno-detail.component.html'
})
export class AcompanhamentoAlunoDetailComponent implements OnInit {
    acompanhamentoAluno: IAcompanhamentoAluno;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ acompanhamentoAluno }) => {
            this.acompanhamentoAluno = acompanhamentoAluno;
        });
    }

    previousState() {
        window.history.back();
    }
}
