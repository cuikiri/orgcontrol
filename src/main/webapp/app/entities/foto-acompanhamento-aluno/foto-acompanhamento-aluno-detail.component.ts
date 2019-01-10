import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IFotoAcompanhamentoAluno } from 'app/shared/model/foto-acompanhamento-aluno.model';

@Component({
    selector: 'jhi-foto-acompanhamento-aluno-detail',
    templateUrl: './foto-acompanhamento-aluno-detail.component.html'
})
export class FotoAcompanhamentoAlunoDetailComponent implements OnInit {
    fotoAcompanhamentoAluno: IFotoAcompanhamentoAluno;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fotoAcompanhamentoAluno }) => {
            this.fotoAcompanhamentoAluno = fotoAcompanhamentoAluno;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
