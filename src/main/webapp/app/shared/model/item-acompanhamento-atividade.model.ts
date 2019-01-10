import { IRespAtivDescritiva } from 'app/shared/model//resp-ativ-descritiva.model';
import { IRespAtivOptativa } from 'app/shared/model//resp-ativ-optativa.model';
import { IRespostaAtividade } from 'app/shared/model//resposta-atividade.model';
import { IAcompanhamentoAtividade } from 'app/shared/model//acompanhamento-atividade.model';

export const enum TipoResposta {
    DESCRITIVA = 'DESCRITIVA',
    OPTATIVA = 'OPTATIVA',
    MULT_OPTATIVA = 'MULT_OPTATIVA'
}

export interface IItemAcompanhamentoAtividade {
    id?: number;
    tipoResposta?: TipoResposta;
    questao?: string;
    obs?: string;
    respAtivDescritiva?: IRespAtivDescritiva;
    respAtivOptativa?: IRespAtivOptativa;
    respostaAtividades?: IRespostaAtividade[];
    acompanhamentoAtividade?: IAcompanhamentoAtividade;
}

export class ItemAcompanhamentoAtividade implements IItemAcompanhamentoAtividade {
    constructor(
        public id?: number,
        public tipoResposta?: TipoResposta,
        public questao?: string,
        public obs?: string,
        public respAtivDescritiva?: IRespAtivDescritiva,
        public respAtivOptativa?: IRespAtivOptativa,
        public respostaAtividades?: IRespostaAtividade[],
        public acompanhamentoAtividade?: IAcompanhamentoAtividade
    ) {}
}
