import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILocomocao } from 'app/shared/model/locomocao.model';

@Component({
    selector: 'jhi-locomocao-detail',
    templateUrl: './locomocao-detail.component.html'
})
export class LocomocaoDetailComponent implements OnInit {
    locomocao: ILocomocao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ locomocao }) => {
            this.locomocao = locomocao;
        });
    }

    previousState() {
        window.history.back();
    }
}
