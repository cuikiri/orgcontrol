import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipoBloco } from 'app/shared/model/tipo-bloco.model';
import { TipoBlocoService } from './tipo-bloco.service';
import { IBloco } from 'app/shared/model/bloco.model';
import { BlocoService } from 'app/entities/bloco';

@Component({
    selector: 'jhi-tipo-bloco-update',
    templateUrl: './tipo-bloco-update.component.html'
})
export class TipoBlocoUpdateComponent implements OnInit {
    tipoBloco: ITipoBloco;
    isSaving: boolean;

    blocos: IBloco[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tipoBlocoService: TipoBlocoService,
        private blocoService: BlocoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoBloco }) => {
            this.tipoBloco = tipoBloco;
        });
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
        if (this.tipoBloco.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoBlocoService.update(this.tipoBloco));
        } else {
            this.subscribeToSaveResponse(this.tipoBlocoService.create(this.tipoBloco));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoBloco>>) {
        result.subscribe((res: HttpResponse<ITipoBloco>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBlocoById(index: number, item: IBloco) {
        return item.id;
    }
}
