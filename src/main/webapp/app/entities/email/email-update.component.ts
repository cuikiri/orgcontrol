import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEmail } from 'app/shared/model/email.model';
import { EmailService } from './email.service';
import { IPessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from 'app/entities/pessoa';
import { IUnidade } from 'app/shared/model/unidade.model';
import { UnidadeService } from 'app/entities/unidade';

@Component({
    selector: 'jhi-email-update',
    templateUrl: './email-update.component.html'
})
export class EmailUpdateComponent implements OnInit {
    email: IEmail;
    isSaving: boolean;

    pessoas: IPessoa[];

    unidades: IUnidade[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private emailService: EmailService,
        private pessoaService: PessoaService,
        private unidadeService: UnidadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ email }) => {
            this.email = email;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.email.id !== undefined) {
            this.subscribeToSaveResponse(this.emailService.update(this.email));
        } else {
            this.subscribeToSaveResponse(this.emailService.create(this.email));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEmail>>) {
        result.subscribe((res: HttpResponse<IEmail>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUnidadeById(index: number, item: IUnidade) {
        return item.id;
    }
}
