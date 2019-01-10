import { IAvaliacao } from 'app/shared/model//avaliacao.model';

export interface ITipoAvaliacao {
    id?: number;
    nome?: string;
    obs?: string;
    avaliacao?: IAvaliacao;
}

export class TipoAvaliacao implements ITipoAvaliacao {
    constructor(public id?: number, public nome?: string, public obs?: string, public avaliacao?: IAvaliacao) {}
}
