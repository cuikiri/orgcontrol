import { IColaborador } from 'app/shared/model//colaborador.model';

export interface ITipoColaborador {
    id?: number;
    nome?: string;
    obs?: string;
    colaborador?: IColaborador;
}

export class TipoColaborador implements ITipoColaborador {
    constructor(public id?: number, public nome?: string, public obs?: string, public colaborador?: IColaborador) {}
}
