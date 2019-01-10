import { Moment } from 'moment';
import { IItemAcompanhamentoAtividade } from 'app/shared/model//item-acompanhamento-atividade.model';
import { IRespostaAtividade } from 'app/shared/model//resposta-atividade.model';

export interface IRespAtivDescritiva {
    id?: number;
    data?: Moment;
    resposta?: string;
    itemAcompanhamentoAtividade?: IItemAcompanhamentoAtividade;
    respostaAtividade?: IRespostaAtividade;
}

export class RespAtivDescritiva implements IRespAtivDescritiva {
    constructor(
        public id?: number,
        public data?: Moment,
        public resposta?: string,
        public itemAcompanhamentoAtividade?: IItemAcompanhamentoAtividade,
        public respostaAtividade?: IRespostaAtividade
    ) {}
}
