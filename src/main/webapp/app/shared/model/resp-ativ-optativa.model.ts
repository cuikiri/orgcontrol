import { Moment } from 'moment';
import { IOpcaoRespAtividade } from 'app/shared/model//opcao-resp-atividade.model';
import { IItemAcompanhamentoAtividade } from 'app/shared/model//item-acompanhamento-atividade.model';
import { IRespostaAtividade } from 'app/shared/model//resposta-atividade.model';

export interface IRespAtivOptativa {
    id?: number;
    data?: Moment;
    opcaoRespostas?: IOpcaoRespAtividade[];
    itemAcompanhamentoAtividade?: IItemAcompanhamentoAtividade;
    respostaAtividade?: IRespostaAtividade;
}

export class RespAtivOptativa implements IRespAtivOptativa {
    constructor(
        public id?: number,
        public data?: Moment,
        public opcaoRespostas?: IOpcaoRespAtividade[],
        public itemAcompanhamentoAtividade?: IItemAcompanhamentoAtividade,
        public respostaAtividade?: IRespostaAtividade
    ) {}
}
