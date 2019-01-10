import { Moment } from 'moment';
import { IOpcaoRespAvalOptativaEconomica } from 'app/shared/model//opcao-resp-aval-optativa-economica.model';
import { IItemAvaliacaoEconomica } from 'app/shared/model//item-avaliacao-economica.model';
import { IRespostaAvaliacaoEconomica } from 'app/shared/model//resposta-avaliacao-economica.model';

export interface IRespAvalOptativaEconomica {
    id?: number;
    data?: Moment;
    opcaoRespostas?: IOpcaoRespAvalOptativaEconomica[];
    itemAvaliacaoEconomica?: IItemAvaliacaoEconomica;
    respostaAvaliacaoEconomica?: IRespostaAvaliacaoEconomica;
}

export class RespAvalOptativaEconomica implements IRespAvalOptativaEconomica {
    constructor(
        public id?: number,
        public data?: Moment,
        public opcaoRespostas?: IOpcaoRespAvalOptativaEconomica[],
        public itemAvaliacaoEconomica?: IItemAvaliacaoEconomica,
        public respostaAvaliacaoEconomica?: IRespostaAvaliacaoEconomica
    ) {}
}
