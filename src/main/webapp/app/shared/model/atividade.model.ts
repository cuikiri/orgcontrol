import { Moment } from 'moment';
import { ITipoAtividade } from 'app/shared/model//tipo-atividade.model';
import { IConteudoProgramatico } from 'app/shared/model//conteudo-programatico.model';
import { IAcompanhamentoAtividade } from 'app/shared/model//acompanhamento-atividade.model';
import { IAluno } from 'app/shared/model//aluno.model';
import { IDiario } from 'app/shared/model//diario.model';
import { IBimestre } from 'app/shared/model//bimestre.model';

export interface IAtividade {
    id?: number;
    nome?: string;
    data?: Moment;
    resumo?: string;
    obs?: string;
    tipoAtividade?: ITipoAtividade;
    conteudoProgramaticos?: IConteudoProgramatico[];
    acompanhamentoAtividades?: IAcompanhamentoAtividade[];
    alunos?: IAluno[];
    diario?: IDiario;
    bimestre?: IBimestre;
}

export class Atividade implements IAtividade {
    constructor(
        public id?: number,
        public nome?: string,
        public data?: Moment,
        public resumo?: string,
        public obs?: string,
        public tipoAtividade?: ITipoAtividade,
        public conteudoProgramaticos?: IConteudoProgramatico[],
        public acompanhamentoAtividades?: IAcompanhamentoAtividade[],
        public alunos?: IAluno[],
        public diario?: IDiario,
        public bimestre?: IBimestre
    ) {}
}
