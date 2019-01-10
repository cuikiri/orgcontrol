import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHorarioMateria } from 'app/shared/model/horario-materia.model';

@Component({
    selector: 'jhi-horario-materia-detail',
    templateUrl: './horario-materia-detail.component.html'
})
export class HorarioMateriaDetailComponent implements OnInit {
    horarioMateria: IHorarioMateria;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ horarioMateria }) => {
            this.horarioMateria = horarioMateria;
        });
    }

    previousState() {
        window.history.back();
    }
}
