import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDadoBiologico } from 'app/shared/model/dado-biologico.model';

@Component({
    selector: 'jhi-dado-biologico-detail',
    templateUrl: './dado-biologico-detail.component.html'
})
export class DadoBiologicoDetailComponent implements OnInit {
    dadoBiologico: IDadoBiologico;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dadoBiologico }) => {
            this.dadoBiologico = dadoBiologico;
        });
    }

    previousState() {
        window.history.back();
    }
}
