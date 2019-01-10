import { Moment } from 'moment';
import { IRespAvalDescritiva } from 'app/shared/model//resp-aval-descritiva.model';
import { IRespAvalOptativa } from 'app/shared/model//resp-aval-optativa.model';
import { IConceito } from 'app/shared/model//conceito.model';
import { IItemAvaliacao } from 'app/shared/model//item-avaliacao.model';

export interface IRespostaAvaliacao {
    id?: number;
    data?: Moment;
    obs?: string;
    respAvalDescritiva?: IRespAvalDescritiva;
    respAvalOptativa?: IRespAvalOptativa;
    conceito?: IConceito;
    itemAvaliacao?: IItemAvaliacao;
}

export class RespostaAvaliacao implements IRespostaAvaliacao {
    constructor(
        public id?: number,
        public data?: Moment,
        public obs?: string,
        public respAvalDescritiva?: IRespAvalDescritiva,
        public respAvalOptativa?: IRespAvalOptativa,
        public conceito?: IConceito,
        public itemAvaliacao?: IItemAvaliacao
    ) {}
}
