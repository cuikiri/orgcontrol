import { IEndereco } from 'app/shared/model//endereco.model';

export const enum Regiao {
    CENTRAL = 'CENTRAL',
    LESTE = 'LESTE',
    OESTE = 'OESTE',
    NORTE = 'NORTE',
    SUL = 'SUL',
    SUDESTE = 'SUDESTE',
    NORDESTE = 'NORDESTE'
}

export interface IUf {
    id?: number;
    nome?: string;
    estdo?: string;
    regiao?: Regiao;
    endereco?: IEndereco;
}

export class Uf implements IUf {
    constructor(public id?: number, public nome?: string, public estdo?: string, public regiao?: Regiao, public endereco?: IEndereco) {}
}
