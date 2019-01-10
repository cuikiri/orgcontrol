import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';

@Component({
    selector: 'jhi-calendario-instituicao-detail',
    templateUrl: './calendario-instituicao-detail.component.html'
})
export class CalendarioInstituicaoDetailComponent implements OnInit {
    calendarioInstituicao: ICalendarioInstituicao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ calendarioInstituicao }) => {
            this.calendarioInstituicao = calendarioInstituicao;
        });
    }

    previousState() {
        window.history.back();
    }
}
