import { IRespAvalOptativa } from 'app/shared/model//resp-aval-optativa.model';

export interface IOpcaoRespAvalOptativa {
    id?: number;
    opcao?: string;
    obs?: string;
    opcaoResposta?: IRespAvalOptativa;
}

export class OpcaoRespAvalOptativa implements IOpcaoRespAvalOptativa {
    constructor(public id?: number, public opcao?: string, public obs?: string, public opcaoResposta?: IRespAvalOptativa) {}
}
