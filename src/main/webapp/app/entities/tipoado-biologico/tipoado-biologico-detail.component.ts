import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoadoBiologico } from 'app/shared/model/tipoado-biologico.model';

@Component({
    selector: 'jhi-tipoado-biologico-detail',
    templateUrl: './tipoado-biologico-detail.component.html'
})
export class TipoadoBiologicoDetailComponent implements OnInit {
    tipoadoBiologico: ITipoadoBiologico;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoadoBiologico }) => {
            this.tipoadoBiologico = tipoadoBiologico;
        });
    }

    previousState() {
        window.history.back();
    }
}
