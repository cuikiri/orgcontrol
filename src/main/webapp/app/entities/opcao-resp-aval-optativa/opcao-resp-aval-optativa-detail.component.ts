import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOpcaoRespAvalOptativa } from 'app/shared/model/opcao-resp-aval-optativa.model';

@Component({
    selector: 'jhi-opcao-resp-aval-optativa-detail',
    templateUrl: './opcao-resp-aval-optativa-detail.component.html'
})
export class OpcaoRespAvalOptativaDetailComponent implements OnInit {
    opcaoRespAvalOptativa: IOpcaoRespAvalOptativa;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ opcaoRespAvalOptativa }) => {
            this.opcaoRespAvalOptativa = opcaoRespAvalOptativa;
        });
    }

    previousState() {
        window.history.back();
    }
}
