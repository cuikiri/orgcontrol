import { IItemPlanejamentoAtividade } from 'app/shared/model//item-planejamento-atividade.model';
import { ICalendarioInstituicao } from 'app/shared/model//calendario-instituicao.model';

export interface IPlanejamentoAtividade {
    id?: number;
    nome?: string;
    objetivo?: string;
    obs?: string;
    itemPlanejamentoAtividades?: IItemPlanejamentoAtividade[];
    calendarioInstituicao?: ICalendarioInstituicao;
}

export class PlanejamentoAtividade implements IPlanejamentoAtividade {
    constructor(
        public id?: number,
        public nome?: string,
        public objetivo?: string,
        public obs?: string,
        public itemPlanejamentoAtividades?: IItemPlanejamentoAtividade[],
        public calendarioInstituicao?: ICalendarioInstituicao
    ) {}
}
