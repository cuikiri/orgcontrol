import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRespAvalOptativa } from 'app/shared/model/resp-aval-optativa.model';

@Component({
    selector: 'jhi-resp-aval-optativa-detail',
    templateUrl: './resp-aval-optativa-detail.component.html'
})
export class RespAvalOptativaDetailComponent implements OnInit {
    respAvalOptativa: IRespAvalOptativa;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respAvalOptativa }) => {
            this.respAvalOptativa = respAvalOptativa;
        });
    }

    previousState() {
        window.history.back();
    }
}
