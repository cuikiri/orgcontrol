import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMateria } from 'app/shared/model/materia.model';

@Component({
    selector: 'jhi-materia-detail',
    templateUrl: './materia-detail.component.html'
})
export class MateriaDetailComponent implements OnInit {
    materia: IMateria;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ materia }) => {
            this.materia = materia;
        });
    }

    previousState() {
        window.history.back();
    }
}
