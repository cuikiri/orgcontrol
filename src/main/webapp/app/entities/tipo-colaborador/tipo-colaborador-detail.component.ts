import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoColaborador } from 'app/shared/model/tipo-colaborador.model';

@Component({
    selector: 'jhi-tipo-colaborador-detail',
    templateUrl: './tipo-colaborador-detail.component.html'
})
export class TipoColaboradorDetailComponent implements OnInit {
    tipoColaborador: ITipoColaborador;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoColaborador }) => {
            this.tipoColaborador = tipoColaborador;
        });
    }

    previousState() {
        window.history.back();
    }
}
