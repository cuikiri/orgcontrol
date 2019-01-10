import { IPlanejamentoInstituicao } from 'app/shared/model//planejamento-instituicao.model';

export interface IItemPlanejamentoInstituicao {
    id?: number;
    nome?: string;
    descricao?: string;
    planejamentoInstituicao?: IPlanejamentoInstituicao;
}

export class ItemPlanejamentoInstituicao implements IItemPlanejamentoInstituicao {
    constructor(
        public id?: number,
        public nome?: string,
        public descricao?: string,
        public planejamentoInstituicao?: IPlanejamentoInstituicao
    ) {}
}
