import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReligiao } from 'app/shared/model/religiao.model';

@Component({
    selector: 'jhi-religiao-detail',
    templateUrl: './religiao-detail.component.html'
})
export class ReligiaoDetailComponent implements OnInit {
    religiao: IReligiao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ religiao }) => {
            this.religiao = religiao;
        });
    }

    previousState() {
        window.history.back();
    }
}
