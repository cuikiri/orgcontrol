import { IAtividade } from 'app/shared/model//atividade.model';

export interface ITipoAtividade {
    id?: number;
    nome?: string;
    atividade?: IAtividade;
}

export class TipoAtividade implements ITipoAtividade {
    constructor(public id?: number, public nome?: string, public atividade?: IAtividade) {}
}
