import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAviso } from 'app/shared/model/aviso.model';
import { AvisoService } from './aviso.service';
import { IPessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from 'app/entities/pessoa';

@Component({
    selector: 'jhi-aviso-update',
    templateUrl: './aviso-update.component.html'
})
export class AvisoUpdateComponent implements OnInit {
    aviso: IAviso;
    isSaving: boolean;

    pessoas: IPessoa[];
    dataDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private avisoService: AvisoService,
        private pessoaService: PessoaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ aviso }) => {
            this.aviso = aviso;
        });
        this.pessoaService.query().subscribe(
            (res: HttpResponse<IPessoa[]>) => {
                this.pessoas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.aviso.id !== undefined) {
            this.subscribeToSaveResponse(this.avisoService.update(this.aviso));
        } else {
            this.subscribeToSaveResponse(this.avisoService.create(this.aviso));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAviso>>) {
        result.subscribe((res: HttpResponse<IAviso>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPessoaById(index: number, item: IPessoa) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
