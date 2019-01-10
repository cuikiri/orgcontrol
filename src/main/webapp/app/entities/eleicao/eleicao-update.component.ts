import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IEleicao } from 'app/shared/model/eleicao.model';
import { EleicaoService } from './eleicao.service';
import { IChapa } from 'app/shared/model/chapa.model';
import { ChapaService } from 'app/entities/chapa';
import { IInstituicao } from 'app/shared/model/instituicao.model';
import { InstituicaoService } from 'app/entities/instituicao';
import { IUnidade } from 'app/shared/model/unidade.model';
import { UnidadeService } from 'app/entities/unidade';

@Component({
    selector: 'jhi-eleicao-update',
    templateUrl: './eleicao-update.component.html'
})
export class EleicaoUpdateComponent implements OnInit {
    eleicao: IEleicao;
    isSaving: boolean;

    chapaganhadoras: IChapa[];

    instituicaos: IInstituicao[];

    unidades: IUnidade[];
    dataCadastroDp: any;
    dataPleitoDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private eleicaoService: EleicaoService,
        private chapaService: ChapaService,
        private instituicaoService: InstituicaoService,
        private unidadeService: UnidadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ eleicao }) => {
            this.eleicao = eleicao;
        });
        this.chapaService.query({ filter: 'eleicaoganhadora-is-null' }).subscribe(
            (res: HttpResponse<IChapa[]>) => {
                if (!this.eleicao.chapaGanhadora || !this.eleicao.chapaGanhadora.id) {
                    this.chapaganhadoras = res.body;
                } else {
                    this.chapaService.find(this.eleicao.chapaGanhadora.id).subscribe(
                        (subRes: HttpResponse<IChapa>) => {
                            this.chapaganhadoras = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.instituicaoService.query().subscribe(
            (res: HttpResponse<IInstituicao[]>) => {
                this.instituicaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.unidadeService.query().subscribe(
            (res: HttpResponse<IUnidade[]>) => {
                this.unidades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.eleicao.id !== undefined) {
            this.subscribeToSaveResponse(this.eleicaoService.update(this.eleicao));
        } else {
            this.subscribeToSaveResponse(this.eleicaoService.create(this.eleicao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEleicao>>) {
        result.subscribe((res: HttpResponse<IEleicao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackChapaById(index: number, item: IChapa) {
        return item.id;
    }

    trackInstituicaoById(index: number, item: IInstituicao) {
        return item.id;
    }

    trackUnidadeById(index: number, item: IUnidade) {
        return item.id;
    }
}
