import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISexo } from 'app/shared/model/sexo.model';

@Component({
    selector: 'jhi-sexo-detail',
    templateUrl: './sexo-detail.component.html'
})
export class SexoDetailComponent implements OnInit {
    sexo: ISexo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sexo }) => {
            this.sexo = sexo;
        });
    }

    previousState() {
        window.history.back();
    }
}
