import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMatricula } from 'app/shared/model/matricula.model';

@Component({
    selector: 'jhi-matricula-detail',
    templateUrl: './matricula-detail.component.html'
})
export class MatriculaDetailComponent implements OnInit {
    matricula: IMatricula;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ matricula }) => {
            this.matricula = matricula;
        });
    }

    previousState() {
        window.history.back();
    }
}
