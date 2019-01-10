import { Moment } from 'moment';
import { IPessoa } from 'app/shared/model//pessoa.model';

export const enum TipoAviso {
    IMPORTANTE = 'IMPORTANTE',
    URGENTE = 'URGENTE',
    NORMAL = 'NORMAL'
}

export interface IAviso {
    id?: number;
    data?: Moment;
    aviso?: string;
    tipo?: TipoAviso;
    pessoas?: IPessoa[];
}

export class Aviso implements IAviso {
    constructor(public id?: number, public data?: Moment, public aviso?: string, public tipo?: TipoAviso, public pessoas?: IPessoa[]) {}
}
