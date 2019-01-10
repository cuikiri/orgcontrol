import { IPessoa } from 'app/shared/model//pessoa.model';
import { IDadosMedico } from 'app/shared/model//dados-medico.model';
import { ILocomocao } from 'app/shared/model//locomocao.model';
import { IAdvertencia } from 'app/shared/model//advertencia.model';
import { IAvaliacaoEconomica } from 'app/shared/model//avaliacao-economica.model';
import { IAcompanhamentoAluno } from 'app/shared/model//acompanhamento-aluno.model';
import { IAcompanhamentoEscolarAluno } from 'app/shared/model//acompanhamento-escolar-aluno.model';
import { ICaracteristicasPsiquicas } from 'app/shared/model//caracteristicas-psiquicas.model';
import { IRaca } from 'app/shared/model//raca.model';
import { ISexo } from 'app/shared/model//sexo.model';
import { IReligiao } from 'app/shared/model//religiao.model';
import { IUnidade } from 'app/shared/model//unidade.model';
import { IDiario } from 'app/shared/model//diario.model';
import { IResponsavel } from 'app/shared/model//responsavel.model';
import { IAtividade } from 'app/shared/model//atividade.model';
import { IFechamentoBimestre } from 'app/shared/model//fechamento-bimestre.model';

export interface IAluno {
    id?: number;
    apelido?: string;
    numeroIrmaos?: number;
    obs?: string;
    pessoa?: IPessoa;
    mae?: IPessoa;
    pai?: IPessoa;
    dadosMedico?: IDadosMedico;
    irmaos?: IPessoa[];
    locomocaos?: ILocomocao[];
    advertencias?: IAdvertencia[];
    avaliacaoEconomicas?: IAvaliacaoEconomica[];
    acompanhamentoAlunos?: IAcompanhamentoAluno[];
    acompanhamentoEscolarAlunos?: IAcompanhamentoEscolarAluno[];
    caracteristicasPsiquicas?: ICaracteristicasPsiquicas[];
    raca?: IRaca;
    sexo?: ISexo;
    religiao?: IReligiao;
    unidade?: IUnidade;
    diario?: IDiario;
    responsavels?: IResponsavel[];
    atividades?: IAtividade[];
    fechamentoBimestres?: IFechamentoBimestre[];
}

export class Aluno implements IAluno {
    constructor(
        public id?: number,
        public apelido?: string,
        public numeroIrmaos?: number,
        public obs?: string,
        public pessoa?: IPessoa,
        public mae?: IPessoa,
        public pai?: IPessoa,
        public dadosMedico?: IDadosMedico,
        public irmaos?: IPessoa[],
        public locomocaos?: ILocomocao[],
        public advertencias?: IAdvertencia[],
        public avaliacaoEconomicas?: IAvaliacaoEconomica[],
        public acompanhamentoAlunos?: IAcompanhamentoAluno[],
        public acompanhamentoEscolarAlunos?: IAcompanhamentoEscolarAluno[],
        public caracteristicasPsiquicas?: ICaracteristicasPsiquicas[],
        public raca?: IRaca,
        public sexo?: ISexo,
        public religiao?: IReligiao,
        public unidade?: IUnidade,
        public diario?: IDiario,
        public responsavels?: IResponsavel[],
        public atividades?: IAtividade[],
        public fechamentoBimestres?: IFechamentoBimestre[]
    ) {}
}
