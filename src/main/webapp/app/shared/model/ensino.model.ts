import { IColaborador } from 'app/shared/model//colaborador.model';

export interface IEnsino {
    id?: number;
    nome?: string;
    obs?: string;
    colaborador?: IColaborador;
}

export class Ensino implements IEnsino {
    constructor(public id?: number, public nome?: string, public obs?: string, public colaborador?: IColaborador) {}
}
