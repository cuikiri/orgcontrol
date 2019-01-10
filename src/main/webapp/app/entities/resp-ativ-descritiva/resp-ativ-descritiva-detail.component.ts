import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRespAtivDescritiva } from 'app/shared/model/resp-ativ-descritiva.model';

@Component({
    selector: 'jhi-resp-ativ-descritiva-detail',
    templateUrl: './resp-ativ-descritiva-detail.component.html'
})
export class RespAtivDescritivaDetailComponent implements OnInit {
    respAtivDescritiva: IRespAtivDescritiva;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAtivDescritiva }) => {
            this.respAtivDescritiva = respAtivDescritiva;
        });
    }

    previousState() {
        window.history.back();
    }
}
