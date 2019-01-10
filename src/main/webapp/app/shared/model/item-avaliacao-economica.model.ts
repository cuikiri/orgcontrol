import { IRespAvalDescritivaEconomica } from 'app/shared/model//resp-aval-descritiva-economica.model';
import { IRespAvalOptativaEconomica } from 'app/shared/model//resp-aval-optativa-economica.model';
import { IRespostaAvaliacaoEconomica } from 'app/shared/model//resposta-avaliacao-economica.model';
import { IAvaliacaoEconomica } from 'app/shared/model//avaliacao-economica.model';

export const enum TipoResposta {
    DESCRITIVA = 'DESCRITIVA',
    OPTATIVA = 'OPTATIVA',
    MULT_OPTATIVA = 'MULT_OPTATIVA'
}

export interface IItemAvaliacaoEconomica {
    id?: number;
    tipoResposta?: TipoResposta;
    questao?: string;
    obs?: string;
    respAvalDescritivaEconomica?: IRespAvalDescritivaEconomica;
    respAvalOptativaEconomica?: IRespAvalOptativaEconomica;
    respostaAvaliacaoEconomicas?: IRespostaAvaliacaoEconomica[];
    avaliacaoEconomica?: IAvaliacaoEconomica;
}

export class ItemAvaliacaoEconomica implements IItemAvaliacaoEconomica {
    constructor(
        public id?: number,
        public tipoResposta?: TipoResposta,
        public questao?: string,
        public obs?: string,
        public respAvalDescritivaEconomica?: IRespAvalDescritivaEconomica,
        public respAvalOptativaEconomica?: IRespAvalOptativaEconomica,
        public respostaAvaliacaoEconomicas?: IRespostaAvaliacaoEconomica[],
        public avaliacaoEconomica?: IAvaliacaoEconomica
    ) {}
}
