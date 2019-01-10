import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConteudoProgramatico } from 'app/shared/model/conteudo-programatico.model';

@Component({
    selector: 'jhi-conteudo-programatico-detail',
    templateUrl: './conteudo-programatico-detail.component.html'
})
export class ConteudoProgramaticoDetailComponent implements OnInit {
    conteudoProgramatico: IConteudoProgramatico;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conteudoProgramatico }) => {
            this.conteudoProgramatico = conteudoProgramatico;
        });
    }

    previousState() {
        window.history.back();
    }
}
