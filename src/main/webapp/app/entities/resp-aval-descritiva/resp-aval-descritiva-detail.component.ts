import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRespAvalDescritiva } from 'app/shared/model/resp-aval-descritiva.model';

@Component({
    selector: 'jhi-resp-aval-descritiva-detail',
    templateUrl: './resp-aval-descritiva-detail.component.html'
})
export class RespAvalDescritivaDetailComponent implements OnInit {
    respAvalDescritiva: IRespAvalDescritiva;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAvalDescritiva }) => {
            this.respAvalDescritiva = respAvalDescritiva;
        });
    }

    previousState() {
        window.history.back();
    }
}
