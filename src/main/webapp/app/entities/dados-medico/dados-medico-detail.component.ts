import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDadosMedico } from 'app/shared/model/dados-medico.model';

@Component({
    selector: 'jhi-dados-medico-detail',
    templateUrl: './dados-medico-detail.component.html'
})
export class DadosMedicoDetailComponent implements OnInit {
    dadosMedico: IDadosMedico;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dadosMedico }) => {
            this.dadosMedico = dadosMedico;
        });
    }

    previousState() {
        window.history.back();
    }
}
