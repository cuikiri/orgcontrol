import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRespAtivOptativa } from 'app/shared/model/resp-ativ-optativa.model';

@Component({
    selector: 'jhi-resp-ativ-optativa-detail',
    templateUrl: './resp-ativ-optativa-detail.component.html'
})
export class RespAtivOptativaDetailComponent implements OnInit {
    respAtivOptativa: IRespAtivOptativa;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAtivOptativa }) => {
            this.respAtivOptativa = respAtivOptativa;
        });
    }

    previousState() {
        window.history.back();
    }
}
