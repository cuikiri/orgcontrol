import { IBloco } from 'app/shared/model//bloco.model';

export interface ITipoBloco {
    id?: number;
    nome?: string;
    bloco?: IBloco;
}

export class TipoBloco implements ITipoBloco {
    constructor(public id?: number, public nome?: string, public bloco?: IBloco) {}
}
