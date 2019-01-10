import { IItemPlanejamentoInstituicao } from 'app/shared/model//item-planejamento-instituicao.model';
import { ICalendarioInstituicao } from 'app/shared/model//calendario-instituicao.model';

export interface IPlanejamentoInstituicao {
    id?: number;
    nome?: string;
    objetivo?: string;
    obs?: string;
    itemPlanejamentoInstituicaos?: IItemPlanejamentoInstituicao[];
    calendarioInstituicao?: ICalendarioInstituicao;
}

export class PlanejamentoInstituicao implements IPlanejamentoInstituicao {
    constructor(
        public id?: number,
        public nome?: string,
        public objetivo?: string,
        public obs?: string,
        public itemPlanejamentoInstituicaos?: IItemPlanejamentoInstituicao[],
        public calendarioInstituicao?: ICalendarioInstituicao
    ) {}
}
