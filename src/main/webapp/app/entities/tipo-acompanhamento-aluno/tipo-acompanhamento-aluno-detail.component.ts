import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoAcompanhamentoAluno } from 'app/shared/model/tipo-acompanhamento-aluno.model';

@Component({
    selector: 'jhi-tipo-acompanhamento-aluno-detail',
    templateUrl: './tipo-acompanhamento-aluno-detail.component.html'
})
export class TipoAcompanhamentoAlunoDetailComponent implements OnInit {
    tipoAcompanhamentoAluno: ITipoAcompanhamentoAluno;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoAcompanhamentoAluno }) => {
            this.tipoAcompanhamentoAluno = tipoAcompanhamentoAluno;
        });
    }

    previousState() {
        window.history.back();
    }
}
