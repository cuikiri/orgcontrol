import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAcompanhamentoEscolarAluno } from 'app/shared/model/acompanhamento-escolar-aluno.model';

@Component({
    selector: 'jhi-acompanhamento-escolar-aluno-detail',
    templateUrl: './acompanhamento-escolar-aluno-detail.component.html'
})
export class AcompanhamentoEscolarAlunoDetailComponent implements OnInit {
    acompanhamentoEscolarAluno: IAcompanhamentoEscolarAluno;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ acompanhamentoEscolarAluno }) => {
            this.acompanhamentoEscolarAluno = acompanhamentoEscolarAluno;
        });
    }

    previousState() {
        window.history.back();
    }
}
