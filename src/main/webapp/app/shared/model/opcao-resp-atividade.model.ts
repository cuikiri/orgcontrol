import { IRespAtivOptativa } from 'app/shared/model//resp-ativ-optativa.model';

export interface IOpcaoRespAtividade {
    id?: number;
    opcao?: string;
    obs?: string;
    opcaoResposta?: IRespAtivOptativa;
}

export class OpcaoRespAtividade implements IOpcaoRespAtividade {
    constructor(public id?: number, public opcao?: string, public obs?: string, public opcaoResposta?: IRespAtivOptativa) {}
}
