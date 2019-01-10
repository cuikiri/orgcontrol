import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoParteBloco } from 'app/shared/model/tipo-parte-bloco.model';

@Component({
    selector: 'jhi-tipo-parte-bloco-detail',
    templateUrl: './tipo-parte-bloco-detail.component.html'
})
export class TipoParteBlocoDetailComponent implements OnInit {
    tipoParteBloco: ITipoParteBloco;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoParteBloco }) => {
            this.tipoParteBloco = tipoParteBloco;
        });
    }

    previousState() {
        window.history.back();
    }
}
