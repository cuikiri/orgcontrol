import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConceitoAcompanhamento } from 'app/shared/model/conceito-acompanhamento.model';

@Component({
    selector: 'jhi-conceito-acompanhamento-detail',
    templateUrl: './conceito-acompanhamento-detail.component.html'
})
export class ConceitoAcompanhamentoDetailComponent implements OnInit {
    conceitoAcompanhamento: IConceitoAcompanhamento;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conceitoAcompanhamento }) => {
            this.conceitoAcompanhamento = conceitoAcompanhamento;
        });
    }

    previousState() {
        window.history.back();
    }
}
