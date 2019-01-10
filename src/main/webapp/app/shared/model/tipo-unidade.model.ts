import { IUnidade } from 'app/shared/model//unidade.model';

export interface ITipoUnidade {
    id?: number;
    nome?: string;
    obs?: string;
    unidade?: IUnidade;
}

export class TipoUnidade implements ITipoUnidade {
    constructor(public id?: number, public nome?: string, public obs?: string, public unidade?: IUnidade) {}
}
