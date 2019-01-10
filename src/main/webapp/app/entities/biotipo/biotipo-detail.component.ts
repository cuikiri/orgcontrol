import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBiotipo } from 'app/shared/model/biotipo.model';

@Component({
    selector: 'jhi-biotipo-detail',
    templateUrl: './biotipo-detail.component.html'
})
export class BiotipoDetailComponent implements OnInit {
    biotipo: IBiotipo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ biotipo }) => {
            this.biotipo = biotipo;
        });
    }

    previousState() {
        window.history.back();
    }
}
