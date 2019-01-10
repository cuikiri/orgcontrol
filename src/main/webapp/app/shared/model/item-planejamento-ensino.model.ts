import { IPlanejamentoEnsino } from 'app/shared/model//planejamento-ensino.model';

export interface IItemPlanejamentoEnsino {
    id?: number;
    nome?: string;
    descricao?: string;
    planejamentoEnsino?: IPlanejamentoEnsino;
}

export class ItemPlanejamentoEnsino implements IItemPlanejamentoEnsino {
    constructor(public id?: number, public nome?: string, public descricao?: string, public planejamentoEnsino?: IPlanejamentoEnsino) {}
}
