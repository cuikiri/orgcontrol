import { Moment } from 'moment';
import { IItemAvaliacaoEconomica } from 'app/shared/model//item-avaliacao-economica.model';
import { IRespostaAvaliacaoEconomica } from 'app/shared/model//resposta-avaliacao-economica.model';

export interface IRespAvalDescritivaEconomica {
    id?: number;
    data?: Moment;
    resposta?: string;
    itemAvaliacaoEconomica?: IItemAvaliacaoEconomica;
    respostaAvaliacaoEconomica?: IRespostaAvaliacaoEconomica;
}

export class RespAvalDescritivaEconomica implements IRespAvalDescritivaEconomica {
    constructor(
        public id?: number,
        public data?: Moment,
        public resposta?: string,
        public itemAvaliacaoEconomica?: IItemAvaliacaoEconomica,
        public respostaAvaliacaoEconomica?: IRespostaAvaliacaoEconomica
    ) {}
}
