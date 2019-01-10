import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUf } from 'app/shared/model/uf.model';
import { UfService } from './uf.service';
import { IEndereco } from 'app/shared/model/endereco.model';
import { EnderecoService } from 'app/entities/endereco';

@Component({
    selector: 'jhi-uf-update',
    templateUrl: './uf-update.component.html'
})
export class UfUpdateComponent implements OnInit {
    uf: IUf;
    isSaving: boolean;

    enderecos: IEndereco[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private ufService: UfService,
        private enderecoService: EnderecoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ uf }) => {
            this.uf = uf;
        });
        this.enderecoService.query().subscribe(
            (res: HttpResponse<IEndereco[]>) => {
                this.enderecos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.uf.id !== undefined) {
            this.subscribeToSaveResponse(this.ufService.update(this.uf));
        } else {
            this.subscribeToSaveResponse(this.ufService.create(this.uf));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUf>>) {
        result.subscribe((res: HttpResponse<IUf>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
