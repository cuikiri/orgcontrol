import { Moment } from 'moment';
import { IRespAvalDescritivaEconomica } from 'app/shared/model//resp-aval-descritiva-economica.model';
import { IRespAvalOptativaEconomica } from 'app/shared/model//resp-aval-optativa-economica.model';
import { IConceito } from 'app/shared/model//conceito.model';
import { IItemAvaliacaoEconomica } from 'app/shared/model//item-avaliacao-economica.model';

export interface IRespostaAvaliacaoEconomica {
    id?: number;
    data?: Moment;
    obs?: string;
    respAvalDescritivaEconomica?: IRespAvalDescritivaEconomica;
    respAvalOptativaEconomica?: IRespAvalOptativaEconomica;
    conceito?: IConceito;
    itemAvaliacaoEconomica?: IItemAvaliacaoEconomica;
}

export class RespostaAvaliacaoEconomica implements IRespostaAvaliacaoEconomica {
    constructor(
        public id?: number,
        public data?: Moment,
        public obs?: string,
        public respAvalDescritivaEconomica?: IRespAvalDescritivaEconomica,
        public respAvalOptativaEconomica?: IRespAvalOptativaEconomica,
        public conceito?: IConceito,
        public itemAvaliacaoEconomica?: IItemAvaliacaoEconomica
    ) {}
}
