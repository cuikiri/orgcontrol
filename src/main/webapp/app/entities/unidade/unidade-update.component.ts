import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUnidade } from 'app/shared/model/unidade.model';
import { UnidadeService } from './unidade.service';
import { IEndereco } from 'app/shared/model/endereco.model';
import { EnderecoService } from 'app/entities/endereco';
import { ITipoUnidade } from 'app/shared/model/tipo-unidade.model';
import { TipoUnidadeService } from 'app/entities/tipo-unidade';
import { IInstituicao } from 'app/shared/model/instituicao.model';
import { InstituicaoService } from 'app/entities/instituicao';

@Component({
    selector: 'jhi-unidade-update',
    templateUrl: './unidade-update.component.html'
})
export class UnidadeUpdateComponent implements OnInit {
    unidade: IUnidade;
    isSaving: boolean;

    enderecos: IEndereco[];

    tipounidades: ITipoUnidade[];

    instituicaos: IInstituicao[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private unidadeService: UnidadeService,
        private enderecoService: EnderecoService,
        private tipoUnidadeService: TipoUnidadeService,
        private instituicaoService: InstituicaoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ unidade }) => {
            this.unidade = unidade;
        });
        this.enderecoService.query({ filter: 'unidade-is-null' }).subscribe(
            (res: HttpResponse<IEndereco[]>) => {
                if (!this.unidade.endereco || !this.unidade.endereco.id) {
                    this.enderecos = res.body;
                } else {
                    this.enderecoService.find(this.unidade.endereco.id).subscribe(
                        (subRes: HttpResponse<IEndereco>) => {
                            this.enderecos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.tipoUnidadeService.query({ filter: 'unidade-is-null' }).subscribe(
            (res: HttpResponse<ITipoUnidade[]>) => {
                if (!this.unidade.tipoUnidade || !this.unidade.tipoUnidade.id) {
                    this.tipounidades = res.body;
                } else {
                    this.tipoUnidadeService.find(this.unidade.tipoUnidade.id).subscribe(
                        (subRes: HttpResponse<ITipoUnidade>) => {
                            this.tipounidades = [subRes.body].concat(res.body);
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
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.unidade.id !== undefined) {
            this.subscribeToSaveResponse(this.unidadeService.update(this.unidade));
        } else {
            this.subscribeToSaveResponse(this.unidadeService.create(this.unidade));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUnidade>>) {
        result.subscribe((res: HttpResponse<IUnidade>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackEnderecoById(index: number, item: IEndereco) {
        return item.id;
    }

    trackTipoUnidadeById(index: number, item: ITipoUnidade) {
        return item.id;
    }

    trackInstituicaoById(index: number, item: IInstituicao) {
        return item.id;
    }
}
