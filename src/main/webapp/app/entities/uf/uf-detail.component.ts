import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUf } from 'app/shared/model/uf.model';

@Component({
    selector: 'jhi-uf-detail',
    templateUrl: './uf-detail.component.html'
})
export class UfDetailComponent implements OnInit {
    uf: IUf;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ uf }) => {
            this.uf = uf;
        });
    }

    previousState() {
        window.history.back();
    }
}
