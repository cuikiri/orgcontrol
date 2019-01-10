import { Moment } from 'moment';
import { ITipoAvaliacao } from 'app/shared/model//tipo-avaliacao.model';
import { IItemAvaliacao } from 'app/shared/model//item-avaliacao.model';
import { IFechamentoBimestre } from 'app/shared/model//fechamento-bimestre.model';

export interface IAvaliacao {
    id?: number;
    nome?: string;
    data?: Moment;
    tema?: string;
    descricao?: string;
    dataInicio?: Moment;
    dataFim?: Moment;
    obs?: string;
    tipoAvaliacao?: ITipoAvaliacao;
    itemAvaliacaos?: IItemAvaliacao[];
    fechamentoBimestre?: IFechamentoBimestre;
}

export class Avaliacao implements IAvaliacao {
    constructor(
        public id?: number,
        public nome?: string,
        public data?: Moment,
        public tema?: string,
        public descricao?: string,
        public dataInicio?: Moment,
        public dataFim?: Moment,
        public obs?: string,
        public tipoAvaliacao?: ITipoAvaliacao,
        public itemAvaliacaos?: IItemAvaliacao[],
        public fechamentoBimestre?: IFechamentoBimestre
    ) {}
}
