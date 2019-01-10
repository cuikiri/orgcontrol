import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParteBloco } from 'app/shared/model/parte-bloco.model';

@Component({
    selector: 'jhi-parte-bloco-detail',
    templateUrl: './parte-bloco-detail.component.html'
})
export class ParteBlocoDetailComponent implements OnInit {
    parteBloco: IParteBloco;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ parteBloco }) => {
            this.parteBloco = parteBloco;
        });
    }

    previousState() {
        window.history.back();
    }
}
