import { IColaborador } from 'app/shared/model//colaborador.model';

export interface IEstadoCivil {
    id?: number;
    nome?: string;
    obs?: string;
    colaborador?: IColaborador;
}

export class EstadoCivil implements IEstadoCivil {
    constructor(public id?: number, public nome?: string, public obs?: string, public colaborador?: IColaborador) {}
}
