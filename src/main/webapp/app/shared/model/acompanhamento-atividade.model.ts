import { Moment } from 'moment';
import { ITipoAcompanhamentoAtividade } from 'app/shared/model//tipo-acompanhamento-atividade.model';
import { IItemAcompanhamentoAtividade } from 'app/shared/model//item-acompanhamento-atividade.model';
import { IAtividade } from 'app/shared/model//atividade.model';

export interface IAcompanhamentoAtividade {
    id?: number;
    nome?: string;
    data?: Moment;
    tema?: string;
    descricao?: string;
    dataInicio?: Moment;
    dataFim?: Moment;
    obs?: string;
    tipoAcompanhamentoAtividade?: ITipoAcompanhamentoAtividade;
    itemAcompanhamentoAtividades?: IItemAcompanhamentoAtividade[];
    atividade?: IAtividade;
}

export class AcompanhamentoAtividade implements IAcompanhamentoAtividade {
    constructor(
        public id?: number,
        public nome?: string,
        public data?: Moment,
        public tema?: string,
        public descricao?: string,
        public dataInicio?: Moment,
        public dataFim?: Moment,
        public obs?: string,
        public tipoAcompanhamentoAtividade?: ITipoAcompanhamentoAtividade,
        public itemAcompanhamentoAtividades?: IItemAcompanhamentoAtividade[],
        public atividade?: IAtividade
    ) {}
}
