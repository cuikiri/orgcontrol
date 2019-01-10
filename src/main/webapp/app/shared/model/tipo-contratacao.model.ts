import { IColaborador } from 'app/shared/model//colaborador.model';

export interface ITipoContratacao {
    id?: number;
    nome?: string;
    obs?: string;
    colaborador?: IColaborador;
}

export class TipoContratacao implements ITipoContratacao {
    constructor(public id?: number, public nome?: string, public obs?: string, public colaborador?: IColaborador) {}
}
