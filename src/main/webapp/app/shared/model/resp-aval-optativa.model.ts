import { Moment } from 'moment';
import { IOpcaoRespAvalOptativa } from 'app/shared/model//opcao-resp-aval-optativa.model';
import { IItemAvaliacao } from 'app/shared/model//item-avaliacao.model';
import { IRespostaAvaliacao } from 'app/shared/model//resposta-avaliacao.model';

export interface IRespAvalOptativa {
    id?: number;
    data?: Moment;
    opcaoRespostas?: IOpcaoRespAvalOptativa[];
    itemAvaliacao?: IItemAvaliacao;
    respostaAvaliacao?: IRespostaAvaliacao;
}

export class RespAvalOptativa implements IRespAvalOptativa {
    constructor(
        public id?: number,
        public data?: Moment,
        public opcaoRespostas?: IOpcaoRespAvalOptativa[],
        public itemAvaliacao?: IItemAvaliacao,
        public respostaAvaliacao?: IRespostaAvaliacao
    ) {}
}
