import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from './colaborador.service';
import { ITipoContratacao } from 'app/shared/model/tipo-contratacao.model';
import { TipoContratacaoService } from 'app/entities/tipo-contratacao';
import { ITipoColaborador } from 'app/shared/model/tipo-colaborador.model';
import { TipoColaboradorService } from 'app/entities/tipo-colaborador';
import { IEstadoCivil } from 'app/shared/model/estado-civil.model';
import { EstadoCivilService } from 'app/entities/estado-civil';
import { IPessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from 'app/entities/pessoa';
import { IDadosMedico } from 'app/shared/model/dados-medico.model';
import { DadosMedicoService } from 'app/entities/dados-medico';
import { ICandidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from 'app/entities/candidato';
import { IDiario } from 'app/shared/model/diario.model';
import { DiarioService } from 'app/entities/diario';
import { IInstituicao } from 'app/shared/model/instituicao.model';
import { InstituicaoService } from 'app/entities/instituicao';
import { IUnidade } from 'app/shared/model/unidade.model';
import { UnidadeService } from 'app/entities/unidade';

@Component({
    selector: 'jhi-colaborador-update',
    templateUrl: './colaborador-update.component.html'
})
export class ColaboradorUpdateComponent implements OnInit {
    colaborador: IColaborador;
    isSaving: boolean;

    tipocontratacaos: ITipoContratacao[];

    tipocolaboradors: ITipoColaborador[];

    estadocivils: IEstadoCivil[];

    pessoas: IPessoa[];

    dadosmedicos: IDadosMedico[];

    candidatoes: ICandidato[];

    diarios: IDiario[];

    instituicaos: IInstituicao[];

    unidades: IUnidade[];
    dataCadastroDp: any;
    dataAdmissaoDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private colaboradorService: ColaboradorService,
        private tipoContratacaoService: TipoContratacaoService,
        private tipoColaboradorService: TipoColaboradorService,
        private estadoCivilService: EstadoCivilService,
        private pessoaService: PessoaService,
        private dadosMedicoService: DadosMedicoService,
        private candidatoService: CandidatoService,
        private diarioService: DiarioService,
        private instituicaoService: InstituicaoService,
        private unidadeService: UnidadeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ colaborador }) => {
            this.colaborador = colaborador;
        });
        this.tipoContratacaoService.query({ filter: 'colaborador-is-null' }).subscribe(
            (res: HttpResponse<ITipoContratacao[]>) => {
                if (!this.colaborador.tipoContratacao || !this.colaborador.tipoContratacao.id) {
                    this.tipocontratacaos = res.body;
                } else {
                    this.tipoContratacaoService.find(this.colaborador.tipoContratacao.id).subscribe(
                        (subRes: HttpResponse<ITipoContratacao>) => {
                            this.tipocontratacaos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.tipoColaboradorService.query({ filter: 'colaborador-is-null' }).subscribe(
            (res: HttpResponse<ITipoColaborador[]>) => {
                if (!this.colaborador.tipoColaborador || !this.colaborador.tipoColaborador.id) {
                    this.tipocolaboradors = res.body;
                } else {
                    this.tipoColaboradorService.find(this.colaborador.tipoColaborador.id).subscribe(
                        (subRes: HttpResponse<ITipoColaborador>) => {
                            this.tipocolaboradors = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.estadoCivilService.query({ filter: 'colaborador-is-null' }).subscribe(
            (res: HttpResponse<IEstadoCivil[]>) => {
                if (!this.colaborador.estadoCivil || !this.colaborador.estadoCivil.id) {
                    this.estadocivils = res.body;
                } else {
                    this.estadoCivilService.find(this.colaborador.estadoCivil.id).subscribe(
                        (subRes: HttpResponse<IEstadoCivil>) => {
                            this.estadocivils = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.pessoaService.query({ filter: 'colaborador-is-null' }).subscribe(
            (res: HttpResponse<IPessoa[]>) => {
                if (!this.colaborador.pessoa || !this.colaborador.pessoa.id) {
                    this.pessoas = res.body;
                } else {
                    this.pessoaService.find(this.colaborador.pessoa.id).subscribe(
                        (subRes: HttpResponse<IPessoa>) => {
                            this.pessoas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.dadosMedicoService.query({ filter: 'colaborador-is-null' }).subscribe(
            (res: HttpResponse<IDadosMedico[]>) => {
                if (!this.colaborador.dadosMedico || !this.colaborador.dadosMedico.id) {
                    this.dadosmedicos = res.body;
                } else {
                    this.dadosMedicoService.find(this.colaborador.dadosMedico.id).subscribe(
                        (subRes: HttpResponse<IDadosMedico>) => {
                            this.dadosmedicos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.candidatoService.query().subscribe(
            (res: HttpResponse<ICandidato[]>) => {
                this.candidatoes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.diarioService.query().subscribe(
            (res: HttpResponse<IDiario[]>) => {
                this.diarios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.instituicaoService.query().subscribe(
            (res: HttpResponse<IInstituicao[]>) => {
                this.instituicaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.unidadeService.query().subscribe(
            (res: HttpResponse<IUnidade[]>) => {
                this.unidades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.colaborador.id !== undefined) {
            this.subscribeToSaveResponse(this.colaboradorService.update(this.colaborador));
        } else {
            this.subscribeToSaveResponse(this.colaboradorService.create(this.colaborador));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IColaborador>>) {
        result.subscribe((res: HttpResponse<IColaborador>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTipoContratacaoById(index: number, item: ITipoContratacao) {
        return item.id;
    }

    trackTipoColaboradorById(index: number, item: ITipoColaborador) {
        return item.id;
    }

    trackEstadoCivilById(index: number, item: IEstadoCivil) {
        return item.id;
    }

    trackPessoaById(index: number, item: IPessoa) {
        return item.id;
    }

    trackDadosMedicoById(index: number, item: IDadosMedico) {
        return item.id;
    }

    trackCandidatoById(index: number, item: ICandidato) {
        return item.id;
    }

    trackDiarioById(index: number, item: IDiario) {
        return item.id;
    }

    trackInstituicaoById(index: number, item: IInstituicao) {
        return item.id;
    }

    trackUnidadeById(index: number, item: IUnidade) {
        return item.id;
    }
}
