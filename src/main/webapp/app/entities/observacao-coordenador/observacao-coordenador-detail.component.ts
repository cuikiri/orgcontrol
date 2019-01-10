import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IObservacaoCoordenador } from 'app/shared/model/observacao-coordenador.model';

@Component({
    selector: 'jhi-observacao-coordenador-detail',
    templateUrl: './observacao-coordenador-detail.component.html'
})
export class ObservacaoCoordenadorDetailComponent implements OnInit {
    observacaoCoordenador: IObservacaoCoordenador;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ observacaoCoordenador }) => {
            this.observacaoCoordenador = observacaoCoordenador;
        });
    }

    previousState() {
        window.history.back();
    }
}
