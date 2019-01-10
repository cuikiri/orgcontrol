import { Moment } from 'moment';
import { IRespAtivDescritiva } from 'app/shared/model//resp-ativ-descritiva.model';
import { IRespAtivOptativa } from 'app/shared/model//resp-ativ-optativa.model';
import { IConceito } from 'app/shared/model//conceito.model';
import { IItemAcompanhamentoAtividade } from 'app/shared/model//item-acompanhamento-atividade.model';

export interface IRespostaAtividade {
    id?: number;
    data?: Moment;
    obs?: string;
    respAtivDescritiva?: IRespAtivDescritiva;
    respAtivOptativa?: IRespAtivOptativa;
    conceito?: IConceito;
    itemAcompanhamentoAtividade?: IItemAcompanhamentoAtividade;
}

export class RespostaAtividade implements IRespostaAtividade {
    constructor(
        public id?: number,
        public data?: Moment,
        public obs?: string,
        public respAtivDescritiva?: IRespAtivDescritiva,
        public respAtivOptativa?: IRespAtivOptativa,
        public conceito?: IConceito,
        public itemAcompanhamentoAtividade?: IItemAcompanhamentoAtividade
    ) {}
}
