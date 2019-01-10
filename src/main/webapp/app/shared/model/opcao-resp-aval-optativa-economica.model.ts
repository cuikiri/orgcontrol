import { IRespAvalOptativaEconomica } from 'app/shared/model//resp-aval-optativa-economica.model';

export interface IOpcaoRespAvalOptativaEconomica {
    id?: number;
    opcao?: string;
    obs?: string;
    opcaoResposta?: IRespAvalOptativaEconomica;
}

export class OpcaoRespAvalOptativaEconomica implements IOpcaoRespAvalOptativaEconomica {
    constructor(public id?: number, public opcao?: string, public obs?: string, public opcaoResposta?: IRespAvalOptativaEconomica) {}
}
