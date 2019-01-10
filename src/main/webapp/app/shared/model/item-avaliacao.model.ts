import { IRespAvalDescritiva } from 'app/shared/model//resp-aval-descritiva.model';
import { IRespAvalOptativa } from 'app/shared/model//resp-aval-optativa.model';
import { IRespostaAvaliacao } from 'app/shared/model//resposta-avaliacao.model';
import { IAvaliacao } from 'app/shared/model//avaliacao.model';

export const enum TipoResposta {
    DESCRITIVA = 'DESCRITIVA',
    OPTATIVA = 'OPTATIVA',
    MULT_OPTATIVA = 'MULT_OPTATIVA'
}

export interface IItemAvaliacao {
    id?: number;
    tipoResposta?: TipoResposta;
    questao?: string;
    obs?: string;
    respAvalDescritiva?: IRespAvalDescritiva;
    respAvalOptativa?: IRespAvalOptativa;
    respostaAvaliacaos?: IRespostaAvaliacao[];
    avaliacao?: IAvaliacao;
}

export class ItemAvaliacao implements IItemAvaliacao {
    constructor(
        public id?: number,
        public tipoResposta?: TipoResposta,
        public questao?: string,
        public obs?: string,
        public respAvalDescritiva?: IRespAvalDescritiva,
        public respAvalOptativa?: IRespAvalOptativa,
        public respostaAvaliacaos?: IRespostaAvaliacao[],
        public avaliacao?: IAvaliacao
    ) {}
}
