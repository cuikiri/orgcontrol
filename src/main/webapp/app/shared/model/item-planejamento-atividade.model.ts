import { IPlanejamentoAtividade } from 'app/shared/model//planejamento-atividade.model';

export interface IItemPlanejamentoAtividade {
    id?: number;
    nome?: string;
    descricao?: string;
    planejamentoAtividade?: IPlanejamentoAtividade;
}

export class ItemPlanejamentoAtividade implements IItemPlanejamentoAtividade {
    constructor(
        public id?: number,
        public nome?: string,
        public descricao?: string,
        public planejamentoAtividade?: IPlanejamentoAtividade
    ) {}
}
