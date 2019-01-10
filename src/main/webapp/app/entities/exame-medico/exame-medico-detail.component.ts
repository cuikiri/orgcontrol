import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExameMedico } from 'app/shared/model/exame-medico.model';

@Component({
    selector: 'jhi-exame-medico-detail',
    templateUrl: './exame-medico-detail.component.html'
})
export class ExameMedicoDetailComponent implements OnInit {
    exameMedico: IExameMedico;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ exameMedico }) => {
            this.exameMedico = exameMedico;
        });
    }

    previousState() {
        window.history.back();
    }
}
