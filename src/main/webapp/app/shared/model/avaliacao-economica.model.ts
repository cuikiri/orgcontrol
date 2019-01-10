import { Moment } from 'moment';
import { ITipoAvaliacaoEconomica } from 'app/shared/model//tipo-avaliacao-economica.model';
import { IItemAvaliacaoEconomica } from 'app/shared/model//item-avaliacao-economica.model';
import { IAluno } from 'app/shared/model//aluno.model';

export interface IAvaliacaoEconomica {
    id?: number;
    nome?: string;
    data?: Moment;
    tema?: string;
    descricao?: string;
    dataInicio?: Moment;
    dataFim?: Moment;
    obs?: string;
    tipoAvaliacaoEconomica?: ITipoAvaliacaoEconomica;
    itemAvaliacaoEconomicas?: IItemAvaliacaoEconomica[];
    aluno?: IAluno;
}

export class AvaliacaoEconomica implements IAvaliacaoEconomica {
    constructor(
        public id?: number,
        public nome?: string,
        public data?: Moment,
        public tema?: string,
        public descricao?: string,
        public dataInicio?: Moment,
        public dataFim?: Moment,
        public obs?: string,
        public tipoAvaliacaoEconomica?: ITipoAvaliacaoEconomica,
        public itemAvaliacaoEconomicas?: IItemAvaliacaoEconomica[],
        public aluno?: IAluno
    ) {}
}
