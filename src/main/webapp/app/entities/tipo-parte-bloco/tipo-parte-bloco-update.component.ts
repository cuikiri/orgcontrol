import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoParteBloco } from 'app/shared/model/tipo-parte-bloco.model';
import { TipoParteBlocoService } from './tipo-parte-bloco.service';
import { IParteBloco } from 'app/shared/model/parte-bloco.model';
import { ParteBlocoService } from 'app/entities/parte-bloco';

@Component({
    selector: 'jhi-tipo-parte-bloco-update',
    templateUrl: './tipo-parte-bloco-update.component.html'
})
export class TipoParteBlocoUpdateComponent implements OnInit {
    tipoParteBloco: ITipoParteBloco;
    isSaving: boolean;

    parteblocos: IParteBloco[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoParteBlocoService: TipoParteBlocoService,
        private parteBlocoService: ParteBlocoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoParteBloco }) => {
            this.tipoParteBloco = tipoParteBloco;
        });
        this.parteBlocoService.query().subscribe(
            (res: HttpResponse<IParteBloco[]>) => {
                this.parteblocos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipoParteBloco.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoParteBlocoService.update(this.tipoParteBloco));
        } else {
            this.subscribeToSaveResponse(this.tipoParteBlocoService.create(this.tipoParteBloco));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoParteBloco>>) {
        result.subscribe((res: HttpResponse<ITipoParteBloco>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackParteBlocoById(index: number, item: IParteBloco) {
        return item.id;
    }
}
