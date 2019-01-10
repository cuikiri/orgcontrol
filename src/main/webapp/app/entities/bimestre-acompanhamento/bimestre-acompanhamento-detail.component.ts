import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBimestreAcompanhamento } from 'app/shared/model/bimestre-acompanhamento.model';

@Component({
    selector: 'jhi-bimestre-acompanhamento-detail',
    templateUrl: './bimestre-acompanhamento-detail.component.html'
})
export class BimestreAcompanhamentoDetailComponent implements OnInit {
    bimestreAcompanhamento: IBimestreAcompanhamento;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bimestreAcompanhamento }) => {
            this.bimestreAcompanhamento = bimestreAcompanhamento;
        });
    }

    previousState() {
        window.history.back();
    }
}
