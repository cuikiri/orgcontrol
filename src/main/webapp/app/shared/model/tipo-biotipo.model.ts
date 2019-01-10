import { IBiotipo } from 'app/shared/model//biotipo.model';

export interface ITipoBiotipo {
    id?: number;
    nome?: string;
    obs?: string;
    biotipo?: IBiotipo;
}

export class TipoBiotipo implements ITipoBiotipo {
    constructor(public id?: number, public nome?: string, public obs?: string, public biotipo?: IBiotipo) {}
}
