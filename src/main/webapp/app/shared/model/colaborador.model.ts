import { Moment } from 'moment';
import { ITipoContratacao } from 'app/shared/model//tipo-contratacao.model';
import { ITipoColaborador } from 'app/shared/model//tipo-colaborador.model';
import { IEstadoCivil } from 'app/shared/model//estado-civil.model';
import { IPessoa } from 'app/shared/model//pessoa.model';
import { IDadosMedico } from 'app/shared/model//dados-medico.model';
import { IEnsino } from 'app/shared/model//ensino.model';
import { IDocumento } from 'app/shared/model//documento.model';
import { IDependenteLegal } from 'app/shared/model//dependente-legal.model';
import { IAgendaColaborador } from 'app/shared/model//agenda-colaborador.model';
import { ICandidato } from 'app/shared/model//candidato.model';
import { IDiario } from 'app/shared/model//diario.model';
import { IInstituicao } from 'app/shared/model//instituicao.model';
import { IUnidade } from 'app/shared/model//unidade.model';

export interface IColaborador {
    id?: number;
    dataCadastro?: Moment;
    dataAdmissao?: Moment;
    salario?: number;
    pai?: string;
    mae?: string;
    conjuge?: string;
    obs?: string;
    tipoContratacao?: ITipoContratacao;
    tipoColaborador?: ITipoColaborador;
    estadoCivil?: IEstadoCivil;
    pessoa?: IPessoa;
    dadosMedico?: IDadosMedico;
    ensinos?: IEnsino[];
    documentos?: IDocumento[];
    dependenteLegals?: IDependenteLegal[];
    agendaColaboradors?: IAgendaColaborador[];
    candidato?: ICandidato;
    diario?: IDiario;
    instituicao?: IInstituicao;
    unidade?: IUnidade;
}

export class Colaborador implements IColaborador {
    constructor(
        public id?: number,
        public dataCadastro?: Moment,
        public dataAdmissao?: Moment,
        public salario?: number,
        public pai?: string,
        public mae?: string,
        public conjuge?: string,
        public obs?: string,
        public tipoContratacao?: ITipoContratacao,
        public tipoColaborador?: ITipoColaborador,
        public estadoCivil?: IEstadoCivil,
        public pessoa?: IPessoa,
        public dadosMedico?: IDadosMedico,
        public ensinos?: IEnsino[],
        public documentos?: IDocumento[],
        public dependenteLegals?: IDependenteLegal[],
        public agendaColaboradors?: IAgendaColaborador[],
        public candidato?: ICandidato,
        public diario?: IDiario,
        public instituicao?: IInstituicao,
        public unidade?: IUnidade
    ) {}
}
