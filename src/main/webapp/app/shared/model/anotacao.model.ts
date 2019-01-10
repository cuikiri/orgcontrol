import { IDiario } from 'app/shared/model//diario.model';

export interface IAnotacao {
    id?: number;
    descricao?: string;
    diario?: IDiario;
}

export class Anotacao implements IAnotacao {
    constructor(public id?: number, public descricao?: string, public diario?: IDiario) {}
}
