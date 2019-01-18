import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDocumento } from 'app/shared/model/documento.model';
import { DocumentoService } from './documento.service';
import { ITipoDocumento } from 'app/shared/model/tipo-documento.model';
import { TipoDocumentoService } from 'app/entities/tipo-documento';
import { IFotoDocumento } from 'app/shared/model/foto-documento.model';
import { FotoDocumentoService } from 'app/entities/foto-documento';
import { IPessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from 'app/entities/pessoa';
import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from 'app/entities/colaborador';
import { IDependenteLegal } from 'app/shared/model/dependente-legal.model';
import { DependenteLegalService } from 'app/entities/dependente-legal';

@Component({
    selector: 'jhi-documento-update',
    templateUrl: './documento-update.component.html'
})
export class DocumentoUpdateComponent implements OnInit {
    documento: IDocumento;
    isSaving: boolean;

    tipodocumentos: ITipoDocumento[];

    fotodocumentos: IFotoDocumento[];

    pessoas: IPessoa[];

    colaboradors: IColaborador[];

    dependentelegals: IDependenteLegal[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private documentoService: DocumentoService,
        private tipoDocumentoService: TipoDocumentoService,
        private fotoDocumentoService: FotoDocumentoService,
        private pessoaService: PessoaService,
        private colaboradorService: ColaboradorService,
        private dependenteLegalService: DependenteLegalService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ documento }) => {
            this.documento = documento;
        });
        this.tipoDocumentoService.query({ filter: 'documento-is-null' }).subscribe(
            (res: HttpResponse<ITipoDocumento[]>) => {
                if (!this.documento.tipoDocumento || !this.documento.tipoDocumento.id) {
                    this.tipodocumentos = res.body;
                } else {
                    this.tipoDocumentoService.find(this.documento.tipoDocumento.id).subscribe(
                        (subRes: HttpResponse<ITipoDocumento>) => {
                            this.tipodocumentos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.fotoDocumentoService.query({ filter: 'documento-is-null' }).subscribe(
            (res: HttpResponse<IFotoDocumento[]>) => {
                if (!this.documento.fotoDocumento || !this.documento.fotoDocumento.id) {
                    this.fotodocumentos = res.body;
                } else {
                    this.fotoDocumentoService.find(this.documento.fotoDocumento.id).subscribe(
                        (subRes: HttpResponse<IFotoDocumento>) => {
                            this.fotodocumentos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.pessoaService.query().subscribe(
            (res: HttpResponse<IPessoa[]>) => {
                this.pessoas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.colaboradorService.query().subscribe(
            (res: HttpResponse<IColaborador[]>) => {
                this.colaboradors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.dependenteLegalService.query().subscribe(
            (res: HttpResponse<IDependenteLegal[]>) => {
                this.dependentelegals = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.documento.id !== undefined) {
            this.subscribeToSaveResponse(this.documentoService.update(this.documento));
        } else {
            this.subscribeToSaveResponse(this.documentoService.create(this.documento));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDocumento>>) {
        result.subscribe((res: HttpResponse<IDocumento>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTipoDocumentoById(index: number, item: ITipoDocumento) {
        return item.id;
    }

    trackFotoDocumentoById(index: number, item: IFotoDocumento) {
        return item.id;
    }

    trackPessoaById(index: number, item: IPessoa) {
        return item.id;
    }

    trackColaboradorById(index: number, item: IColaborador) {
        return item.id;
    }

    trackDependenteLegalById(index: number, item: IDependenteLegal) {
        return item.id;
    }
}
