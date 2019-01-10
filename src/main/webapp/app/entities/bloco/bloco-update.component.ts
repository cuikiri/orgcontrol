import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBloco } from 'app/shared/model/bloco.model';
import { BlocoService } from './bloco.service';
import { ITipoBloco } from 'app/shared/model/tipo-bloco.model';
import { TipoBlocoService } from 'app/entities/tipo-bloco';
import { ILocalizacao } from 'app/shared/model/localizacao.model';
import { LocalizacaoService } from 'app/entities/localizacao';
import { IUnidade } from 'app/shared/model/unidade.model';
import { UnidadeService } from 'app/entities/unidade';

@Component({
    selector: 'jhi-bloco-update',
    templateUrl: './bloco-update.component.html'
})
export class BlocoUpdateComponent implements OnInit {
    bloco: IBloco;
    isSaving: boolean;

    tipoblocos: ITipoBloco[];

    localizacaos: ILocalizacao[];

    unidades: IUnidade[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private blocoService: BlocoService,
        private tipoBlocoService: TipoBlocoService,
        private localizacaoService: LocalizacaoService,
        private unidadeService: UnidadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bloco }) => {
            this.bloco = bloco;
        });
        this.tipoBlocoService.query({ filter: 'bloco-is-null' }).subscribe(
            (res: HttpResponse<ITipoBloco[]>) => {
                if (!this.bloco.tipoBloco || !this.bloco.tipoBloco.id) {
                    this.tipoblocos = res.body;
                } else {
                    this.tipoBlocoService.find(this.bloco.tipoBloco.id).subscribe(
                        (subRes: HttpResponse<ITipoBloco>) => {
                            this.tipoblocos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.localizacaoService.query({ filter: 'bloco-is-null' }).subscribe(
            (res: HttpResponse<ILocalizacao[]>) => {
                if (!this.bloco.localizacao || !this.bloco.localizacao.id) {
                    this.localizacaos = res.body;
                } else {
                    this.localizacaoService.find(this.bloco.localizacao.id).subscribe(
                        (subRes: HttpResponse<ILocalizacao>) => {
                            this.localizacaos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
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
        if (this.bloco.id !== undefined) {
            this.subscribeToSaveResponse(this.blocoService.update(this.bloco));
        } else {
            this.subscribeToSaveResponse(this.blocoService.create(this.bloco));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBloco>>) {
        result.subscribe((res: HttpResponse<IBloco>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTipoBlocoById(index: number, item: ITipoBloco) {
        return item.id;
    }

    trackLocalizacaoById(index: number, item: ILocalizacao) {
        return item.id;
    }

    trackUnidadeById(index: number, item: IUnidade) {
        return item.id;
    }
}
