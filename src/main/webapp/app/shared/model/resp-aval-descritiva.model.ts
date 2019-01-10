import { Moment } from 'moment';
import { IItemAvaliacao } from 'app/shared/model//item-avaliacao.model';
import { IRespostaAvaliacao } from 'app/shared/model//resposta-avaliacao.model';

export interface IRespAvalDescritiva {
    id?: number;
    data?: Moment;
    resposta?: string;
    itemAvaliacao?: IItemAvaliacao;
    respostaAvaliacao?: IRespostaAvaliacao;
}

export class RespAvalDescritiva implements IRespAvalDescritiva {
    constructor(
        public id?: number,
        public data?: Moment,
        public resposta?: string,
        public itemAvaliacao?: IItemAvaliacao,
        public respostaAvaliacao?: IRespostaAvaliacao
    ) {}
}
