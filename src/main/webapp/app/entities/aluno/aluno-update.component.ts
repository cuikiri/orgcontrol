import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from './aluno.service';
import { IPessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from 'app/entities/pessoa';
import { IDadosMedico } from 'app/shared/model/dados-medico.model';
import { DadosMedicoService } from 'app/entities/dados-medico';
import { IRaca } from 'app/shared/model/raca.model';
import { RacaService } from 'app/entities/raca';
import { ISexo } from 'app/shared/model/sexo.model';
import { SexoService } from 'app/entities/sexo';
import { IReligiao } from 'app/shared/model/religiao.model';
import { ReligiaoService } from 'app/entities/religiao';
import { IUnidade } from 'app/shared/model/unidade.model';
import { UnidadeService } from 'app/entities/unidade';
import { IDiario } from 'app/shared/model/diario.model';
import { DiarioService } from 'app/entities/diario';
import { IResponsavel } from 'app/shared/model/responsavel.model';
import { ResponsavelService } from 'app/entities/responsavel';
import { IAtividade } from 'app/shared/model/atividade.model';
import { AtividadeService } from 'app/entities/atividade';
import { IFechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';
import { FechamentoBimestreService } from 'app/entities/fechamento-bimestre';

@Component({
    selector: 'jhi-aluno-update',
    templateUrl: './aluno-update.component.html'
})
export class AlunoUpdateComponent implements OnInit {
    aluno: IAluno;
    isSaving: boolean;

    pessoas: IPessoa[];

    maes: IPessoa[];

    pais: IPessoa[];

    dadosmedicos: IDadosMedico[];

    racas: IRaca[];

    sexos: ISexo[];

    religiaos: IReligiao[];

    unidades: IUnidade[];

    diarios: IDiario[];

    responsavels: IResponsavel[];

    atividades: IAtividade[];

    fechamentobimestres: IFechamentoBimestre[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private alunoService: AlunoService,
        private pessoaService: PessoaService,
        private dadosMedicoService: DadosMedicoService,
        private racaService: RacaService,
        private sexoService: SexoService,
        private religiaoService: ReligiaoService,
        private unidadeService: UnidadeService,
        private diarioService: DiarioService,
        private responsavelService: ResponsavelService,
        private atividadeService: AtividadeService,
        private fechamentoBimestreService: FechamentoBimestreService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ aluno }) => {
            this.aluno = aluno;
        });
        this.pessoaService.query({ filter: 'aluno-is-null' }).subscribe(
            (res: HttpResponse<IPessoa[]>) => {
                if (!this.aluno.pessoa || !this.aluno.pessoa.id) {
                    this.pessoas = res.body;
                } else {
                    this.pessoaService.find(this.aluno.pessoa.id).subscribe(
                        (subRes: HttpResponse<IPessoa>) => {
                            this.pessoas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.pessoaService.query({ filter: 'alunomae-is-null' }).subscribe(
            (res: HttpResponse<IPessoa[]>) => {
                if (!this.aluno.mae || !this.aluno.mae.id) {
                    this.maes = res.body;
                } else {
                    this.pessoaService.find(this.aluno.mae.id).subscribe(
                        (subRes: HttpResponse<IPessoa>) => {
                            this.maes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.pessoaService.query({ filter: 'alunopai-is-null' }).subscribe(
            (res: HttpResponse<IPessoa[]>) => {
                if (!this.aluno.pai || !this.aluno.pai.id) {
                    this.pais = res.body;
                } else {
                    this.pessoaService.find(this.aluno.pai.id).subscribe(
                        (subRes: HttpResponse<IPessoa>) => {
                            this.pais = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.dadosMedicoService.query({ filter: 'aluno-is-null' }).subscribe(
            (res: HttpResponse<IDadosMedico[]>) => {
                if (!this.aluno.dadosMedico || !this.aluno.dadosMedico.id) {
                    this.dadosmedicos = res.body;
                } else {
                    this.dadosMedicoService.find(this.aluno.dadosMedico.id).subscribe(
                        (subRes: HttpResponse<IDadosMedico>) => {
                            this.dadosmedicos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.racaService.query().subscribe(
            (res: HttpResponse<IRaca[]>) => {
                this.racas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sexoService.query().subscribe(
            (res: HttpResponse<ISexo[]>) => {
                this.sexos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.religiaoService.query().subscribe(
            (res: HttpResponse<IReligiao[]>) => {
                this.religiaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.unidadeService.query().subscribe(
            (res: HttpResponse<IUnidade[]>) => {
                this.unidades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.diarioService.query().subscribe(
            (res: HttpResponse<IDiario[]>) => {
                this.diarios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.responsavelService.query().subscribe(
            (res: HttpResponse<IResponsavel[]>) => {
                this.responsavels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.atividadeService.query().subscribe(
            (res: HttpResponse<IAtividade[]>) => {
                this.atividades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.fechamentoBimestreService.query().subscribe(
            (res: HttpResponse<IFechamentoBimestre[]>) => {
                this.fechamentobimestres = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.aluno.id !== undefined) {
            this.subscribeToSaveResponse(this.alunoService.update(this.aluno));
        } else {
            this.subscribeToSaveResponse(this.alunoService.create(this.aluno));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAluno>>) {
        result.subscribe((res: HttpResponse<IAluno>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDadosMedicoById(index: number, item: IDadosMedico) {
        return item.id;
    }

    trackRacaById(index: number, item: IRaca) {
        return item.id;
    }

    trackSexoById(index: number, item: ISexo) {
        return item.id;
    }

    trackReligiaoById(index: number, item: IReligiao) {
        return item.id;
    }

    trackUnidadeById(index: number, item: IUnidade) {
        return item.id;
    }

    trackDiarioById(index: number, item: IDiario) {
        return item.id;
    }

    trackResponsavelById(index: number, item: IResponsavel) {
        return item.id;
    }

    trackAtividadeById(index: number, item: IAtividade) {
        return item.id;
    }

    trackFechamentoBimestreById(index: number, item: IFechamentoBimestre) {
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
