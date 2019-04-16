import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEndereco } from 'app/shared/model/endereco.model';
import { EnderecoService } from './endereco.service';
import { IUf } from 'app/shared/model/uf.model';
import { UfService } from 'app/entities/uf';
import { ILocalizacao } from 'app/shared/model/localizacao.model';
import { LocalizacaoService } from 'app/entities/localizacao';
import { IUnidade } from 'app/shared/model/unidade.model';
import { UnidadeService } from 'app/entities/unidade';
import { IPessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from 'app/entities/pessoa';

@Component({
    selector: 'jhi-endereco-update',
    templateUrl: './endereco-update.component.html'
})
export class EnderecoUpdateComponent implements OnInit {
    endereco: IEndereco;
    isSaving: boolean;

    estados: IUf[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private enderecoService: EnderecoService,
        private ufService: UfService,
        private localizacaoService: LocalizacaoService,
        private unidadeService: UnidadeService,
        private pessoaService: PessoaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ endereco }) => {
            this.endereco = endereco;
        });
        this.ufService.query({ filter: 'endereco-is-null' }).subscribe(
            (res: HttpResponse<IUf[]>) => {
                if (!this.endereco.estado || !this.endereco.estado.id) {
                    this.estados = res.body;
                } else {
                    this.ufService.find(this.endereco.estado.id).subscribe(
                        (subRes: HttpResponse<IUf>) => {
                            this.estados = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.endereco.id !== undefined) {
            this.subscribeToSaveResponse(this.enderecoService.update(this.endereco));
        } else {
            this.subscribeToSaveResponse(this.enderecoService.create(this.endereco));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEndereco>>) {
        result.subscribe((res: HttpResponse<IEndereco>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUfById(index: number, item: IUf) {
        return item.id;
    }

    trackLocalizacaoById(index: number, item: ILocalizacao) {
        return item.id;
    }

    trackUnidadeById(index: number, item: IUnidade) {
        return item.id;
    }

    trackPessoaById(index: number, item: IPessoa) {
        return item.id;
    }
}
