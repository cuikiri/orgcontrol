import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { ICandidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from './candidato.service';
import { ICargo } from 'app/shared/model/cargo.model';
import { CargoService } from 'app/entities/cargo';
import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from 'app/entities/colaborador';
import { IChapa } from 'app/shared/model/chapa.model';
import { ChapaService } from 'app/entities/chapa';

@Component({
    selector: 'jhi-candidato-update',
    templateUrl: './candidato-update.component.html'
})
export class CandidatoUpdateComponent implements OnInit {
    candidato: ICandidato;
    isSaving: boolean;

    cargos: ICargo[];

    colaboradors: IColaborador[];

    chapas: IChapa[];
    dataCadastroDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private candidatoService: CandidatoService,
        private cargoService: CargoService,
        private colaboradorService: ColaboradorService,
        private chapaService: ChapaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ candidato }) => {
            this.candidato = candidato;
        });
        this.cargoService.query({ filter: 'candidato-is-null' }).subscribe(
            (res: HttpResponse<ICargo[]>) => {
                if (!this.candidato.cargo || !this.candidato.cargo.id) {
                    this.cargos = res.body;
                } else {
                    this.cargoService.find(this.candidato.cargo.id).subscribe(
                        (subRes: HttpResponse<ICargo>) => {
                            this.cargos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.colaboradorService.query({ filter: 'candidato-is-null' }).subscribe(
            (res: HttpResponse<IColaborador[]>) => {
                if (!this.candidato.colaborador || !this.candidato.colaborador.id) {
                    this.colaboradors = res.body;
                } else {
                    this.colaboradorService.find(this.candidato.colaborador.id).subscribe(
                        (subRes: HttpResponse<IColaborador>) => {
                            this.colaboradors = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.chapaService.query().subscribe(
            (res: HttpResponse<IChapa[]>) => {
                this.chapas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.candidato.id !== undefined) {
            this.subscribeToSaveResponse(this.candidatoService.update(this.candidato));
        } else {
            this.subscribeToSaveResponse(this.candidatoService.create(this.candidato));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICandidato>>) {
        result.subscribe((res: HttpResponse<ICandidato>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCargoById(index: number, item: ICargo) {
        return item.id;
    }

    trackColaboradorById(index: number, item: IColaborador) {
        return item.id;
    }

    trackChapaById(index: number, item: IChapa) {
        return item.id;
    }
}
