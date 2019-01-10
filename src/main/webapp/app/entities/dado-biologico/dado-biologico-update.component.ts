import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDadoBiologico } from 'app/shared/model/dado-biologico.model';
import { DadoBiologicoService } from './dado-biologico.service';
import { ITipoadoBiologico } from 'app/shared/model/tipoado-biologico.model';
import { TipoadoBiologicoService } from 'app/entities/tipoado-biologico';
import { IDadosMedico } from 'app/shared/model/dados-medico.model';
import { DadosMedicoService } from 'app/entities/dados-medico';

@Component({
    selector: 'jhi-dado-biologico-update',
    templateUrl: './dado-biologico-update.component.html'
})
export class DadoBiologicoUpdateComponent implements OnInit {
    dadoBiologico: IDadoBiologico;
    isSaving: boolean;

    tipoadobiologicos: ITipoadoBiologico[];

    dadosmedicos: IDadosMedico[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private dadoBiologicoService: DadoBiologicoService,
        private tipoadoBiologicoService: TipoadoBiologicoService,
        private dadosMedicoService: DadosMedicoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dadoBiologico }) => {
            this.dadoBiologico = dadoBiologico;
        });
        this.tipoadoBiologicoService.query({ filter: 'dadobiologico-is-null' }).subscribe(
            (res: HttpResponse<ITipoadoBiologico[]>) => {
                if (!this.dadoBiologico.tipoadoBiologico || !this.dadoBiologico.tipoadoBiologico.id) {
                    this.tipoadobiologicos = res.body;
                } else {
                    this.tipoadoBiologicoService.find(this.dadoBiologico.tipoadoBiologico.id).subscribe(
                        (subRes: HttpResponse<ITipoadoBiologico>) => {
                            this.tipoadobiologicos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.dadosMedicoService.query().subscribe(
            (res: HttpResponse<IDadosMedico[]>) => {
                this.dadosmedicos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.dadoBiologico.id !== undefined) {
            this.subscribeToSaveResponse(this.dadoBiologicoService.update(this.dadoBiologico));
        } else {
            this.subscribeToSaveResponse(this.dadoBiologicoService.create(this.dadoBiologico));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDadoBiologico>>) {
        result.subscribe((res: HttpResponse<IDadoBiologico>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTipoadoBiologicoById(index: number, item: ITipoadoBiologico) {
        return item.id;
    }

    trackDadosMedicoById(index: number, item: IDadosMedico) {
        return item.id;
    }
}
