import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDependenteLegal } from 'app/shared/model/dependente-legal.model';

@Component({
    selector: 'jhi-dependente-legal-detail',
    templateUrl: './dependente-legal-detail.component.html'
})
export class DependenteLegalDetailComponent implements OnInit {
    dependenteLegal: IDependenteLegal;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dependenteLegal }) => {
            this.dependenteLegal = dependenteLegal;
        });
    }

    previousState() {
        window.history.back();
    }
}
