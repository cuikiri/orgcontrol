import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAgendaColaborador } from 'app/shared/model/agenda-colaborador.model';

@Component({
    selector: 'jhi-agenda-colaborador-detail',
    templateUrl: './agenda-colaborador-detail.component.html'
})
export class AgendaColaboradorDetailComponent implements OnInit {
    agendaColaborador: IAgendaColaborador;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ agendaColaborador }) => {
            this.agendaColaborador = agendaColaborador;
        });
    }

    previousState() {
        window.history.back();
    }
}
