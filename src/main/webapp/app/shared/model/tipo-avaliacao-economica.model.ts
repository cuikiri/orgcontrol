import { IAvaliacaoEconomica } from 'app/shared/model//avaliacao-economica.model';

export interface ITipoAvaliacaoEconomica {
    id?: number;
    nome?: string;
    obs?: string;
    avaliacaoEconomica?: IAvaliacaoEconomica;
}

export class TipoAvaliacaoEconomica implements ITipoAvaliacaoEconomica {
    constructor(public id?: number, public nome?: string, public obs?: string, public avaliacaoEconomica?: IAvaliacaoEconomica) {}
}
