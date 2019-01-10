import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IParteBloco } from 'app/shared/model/parte-bloco.model';
import { ParteBlocoService } from './parte-bloco.service';
import { ITipoParteBloco } from 'app/shared/model/tipo-parte-bloco.model';
import { TipoParteBlocoService } from 'app/entities/tipo-parte-bloco';
import { IPeriodoAtividade } from 'app/shared/model/periodo-atividade.model';
import { PeriodoAtividadeService } from 'app/entities/periodo-atividade';
import { IBloco } from 'app/shared/model/bloco.model';
import { BlocoService } from 'app/entities/bloco';

@Component({
    selector: 'jhi-parte-bloco-update',
    templateUrl: './parte-bloco-update.component.html'
})
export class ParteBlocoUpdateComponent implements OnInit {
    parteBloco: IParteBloco;
    isSaving: boolean;

    tipoparteblocos: ITipoParteBloco[];

    periodoatividades: IPeriodoAtividade[];

    blocos: IBloco[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private parteBlocoService: ParteBlocoService,
        private tipoParteBlocoService: TipoParteBlocoService,
        private periodoAtividadeService: PeriodoAtividadeService,
        private blocoService: BlocoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ parteBloco }) => {
            this.parteBloco = parteBloco;
        });
        this.tipoParteBlocoService.query({ filter: 'partebloco-is-null' }).subscribe(
            (res: HttpResponse<ITipoParteBloco[]>) => {
                if (!this.parteBloco.tipoParteBloco || !this.parteBloco.tipoParteBloco.id) {
                    this.tipoparteblocos = res.body;
                } else {
                    this.tipoParteBlocoService.find(this.parteBloco.tipoParteBloco.id).subscribe(
                        (subRes: HttpResponse<ITipoParteBloco>) => {
                            this.tipoparteblocos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.periodoAtividadeService.query().subscribe(
            (res: HttpResponse<IPeriodoAtividade[]>) => {
                this.periodoatividades = res.body;
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
        if (this.parteBloco.id !== undefined) {
            this.subscribeToSaveResponse(this.parteBlocoService.update(this.parteBloco));
        } else {
            this.subscribeToSaveResponse(this.parteBlocoService.create(this.parteBloco));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IParteBloco>>) {
        result.subscribe((res: HttpResponse<IParteBloco>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTipoParteBlocoById(index: number, item: ITipoParteBloco) {
        return item.id;
    }

    trackPeriodoAtividadeById(index: number, item: IPeriodoAtividade) {
        return item.id;
    }

    trackBlocoById(index: number, item: IBloco) {
        return item.id;
    }
}
