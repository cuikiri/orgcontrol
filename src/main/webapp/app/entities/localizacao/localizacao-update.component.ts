import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ILocalizacao } from 'app/shared/model/localizacao.model';
import { LocalizacaoService } from './localizacao.service';
import { IEndereco } from 'app/shared/model/endereco.model';
import { EnderecoService } from 'app/entities/endereco';
import { IBloco } from 'app/shared/model/bloco.model';
import { BlocoService } from 'app/entities/bloco';

@Component({
    selector: 'jhi-localizacao-update',
    templateUrl: './localizacao-update.component.html'
})
export class LocalizacaoUpdateComponent implements OnInit {
    localizacao: ILocalizacao;
    isSaving: boolean;

    enderecos: IEndereco[];

    blocos: IBloco[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private localizacaoService: LocalizacaoService,
        private enderecoService: EnderecoService,
        private blocoService: BlocoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ localizacao }) => {
            this.localizacao = localizacao;
        });
        this.enderecoService.query().subscribe(
            (res: HttpResponse<IEndereco[]>) => {
                this.enderecos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.blocoService.query().subscribe(
            (res: HttpResponse<IBloco[]>) => {
                this.blocos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.localizacao.id !== undefined) {
            this.subscribeToSaveResponse(this.localizacaoService.update(this.localizacao));
        } else {
            this.subscribeToSaveResponse(this.localizacaoService.create(this.localizacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILocalizacao>>) {
        result.subscribe((res: HttpResponse<ILocalizacao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBlocoById(index: number, item: IBloco) {
        return item.id;
    }
}
