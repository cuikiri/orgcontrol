import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoBiotipo } from 'app/shared/model/tipo-biotipo.model';

@Component({
    selector: 'jhi-tipo-biotipo-detail',
    templateUrl: './tipo-biotipo-detail.component.html'
})
export class TipoBiotipoDetailComponent implements OnInit {
    tipoBiotipo: ITipoBiotipo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoBiotipo }) => {
            this.tipoBiotipo = tipoBiotipo;
        });
    }

    previousState() {
        window.history.back();
    }
}
