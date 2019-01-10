import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBloco } from 'app/shared/model/bloco.model';

@Component({
    selector: 'jhi-bloco-detail',
    templateUrl: './bloco-detail.component.html'
})
export class BlocoDetailComponent implements OnInit {
    bloco: IBloco;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bloco }) => {
            this.bloco = bloco;
        });
    }

    previousState() {
        window.history.back();
    }
}
