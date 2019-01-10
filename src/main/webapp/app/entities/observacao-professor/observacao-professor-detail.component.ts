import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IObservacaoProfessor } from 'app/shared/model/observacao-professor.model';

@Component({
    selector: 'jhi-observacao-professor-detail',
    templateUrl: './observacao-professor-detail.component.html'
})
export class ObservacaoProfessorDetailComponent implements OnInit {
    observacaoProfessor: IObservacaoProfessor;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ observacaoProfessor }) => {
            this.observacaoProfessor = observacaoProfessor;
        });
    }

    previousState() {
        window.history.back();
    }
}
