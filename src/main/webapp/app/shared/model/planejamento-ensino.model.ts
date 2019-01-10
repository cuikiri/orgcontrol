import { IItemPlanejamentoEnsino } from 'app/shared/model//item-planejamento-ensino.model';
import { ICalendarioInstituicao } from 'app/shared/model//calendario-instituicao.model';

export interface IPlanejamentoEnsino {
    id?: number;
    nome?: string;
    objetivo?: string;
    obs?: string;
    itemPlanejamentoEnsinos?: IItemPlanejamentoEnsino[];
    calendarioInstituicao?: ICalendarioInstituicao;
}

export class PlanejamentoEnsino implements IPlanejamentoEnsino {
    constructor(
        public id?: number,
        public nome?: string,
        public objetivo?: string,
        public obs?: string,
        public itemPlanejamentoEnsinos?: IItemPlanejamentoEnsino[],
        public calendarioInstituicao?: ICalendarioInstituicao
    ) {}
}
