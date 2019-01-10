import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMateriaAcompanhamento } from 'app/shared/model/materia-acompanhamento.model';

@Component({
    selector: 'jhi-materia-acompanhamento-detail',
    templateUrl: './materia-acompanhamento-detail.component.html'
})
export class MateriaAcompanhamentoDetailComponent implements OnInit {
    materiaAcompanhamento: IMateriaAcompanhamento;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ materiaAcompanhamento }) => {
            this.materiaAcompanhamento = materiaAcompanhamento;
        });
    }

    previousState() {
        window.history.back();
    }
}
